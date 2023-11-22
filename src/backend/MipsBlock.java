package backend;

import llvm.type.ArrayType;
import llvm.type.IntegerType;
import llvm.type.PointerType;
import llvm.value.BasicBlock;
import llvm.value.Value;
import llvm.value.constant.ConstInt;
import llvm.value.constant.GlobalArray;
import llvm.value.constant.GlobalValue;
import llvm.value.constant.GlobalVariable;
import llvm.value.instruction.AllocaIns;
import llvm.value.instruction.BrIns;
import llvm.value.instruction.CalculateIns;
import llvm.value.instruction.CallIns;
import llvm.value.instruction.CmpIns;
import llvm.value.instruction.GetIns;
import llvm.value.instruction.Instruction;
import llvm.value.instruction.LoadIns;
import llvm.value.instruction.RetIns;
import llvm.value.instruction.StoreIns;
import llvm.value.instruction.UnaryIns;

import java.util.ArrayList;

public class MipsBlock {
    private BasicBlock BB;
    private BasicBlock nextBB;
    private ArrayList<Instruction> insList;
    private MipsFunction mipsFunction;
    private StringBuilder sb;
    private static int count = 0;
    private ArrayList<MipsIns> mipsInsList;

    public MipsBlock(BasicBlock BB, MipsFunction mipsFunction, BasicBlock nextBB) {
        this.BB = BB;
        this.insList = BB.getInstructions();
        this.mipsFunction = mipsFunction;
        sb = new StringBuilder();
        this.nextBB = nextBB;
        mipsInsList = new ArrayList<>();
    }

    public void build() {
        MipsIns mipsIns;
        sb.append(BB.getName()).append(":\n");
        if (BB.getFather().getBasicBlocks().get(0).equals(BB)) {
            if (BB.getFather().getParamNum() <= 4) {
                for (int i = BB.getFather().getParamNum() - 1; i >= 0; i--) {
                    int temp = (BB.getFather().getParamNum() - 1 - i) * 2;
                    mipsFunction.register[i + 4] = true;
                    mipsFunction.regToVar[i + 4] = BB.getInstructions().get(temp);
                    mipsFunction.varToReg.put(insList.get(temp), i + 4);
                }
            } else {
                for (int i = BB.getFather().getParamNum() - 1; i >= 4; i--) {
                    int temp = (BB.getFather().getParamNum() - 1 - i) * 2;
                    mipsFunction.stack.put(insList.get(temp), mipsFunction.stackP);
                    mipsFunction.stackP += 4;
                    distributeReg(insList.get(temp), temp);
                }
                for (int i = 3; i >= 0; i--) {
                    int temp = (BB.getFather().getParamNum() - 1 - i) * 2;
                    mipsFunction.register[i + 4] = true;
                    mipsFunction.regToVar[i + 4] = BB.getInstructions().get(temp);
                    mipsFunction.varToReg.put(insList.get(temp), i + 4);
                }
            }
            for (int i = BB.getFather().getParamNum() * 2; i < insList.size(); i++) {
                insOutput(insList.get(i));
            }
            return;
        }
        for (int i = 0; i < insList.size(); i++) {
            insOutput(insList.get(i));
        }
    }

    private StringBuilder tempStr = new StringBuilder();

    private void insOutput(Instruction ins) {
        //sb.append("#").append(ins);
        MipsIns mipsIns;
        if (tempStr.length() != 0) {
            if (!(ins instanceof CallIns) || !((CallIns) ins).getFunc().getName().equals("putch")) {
                if (tempStr.length() == 1) {
                    if (mipsFunction.register[4]) {
                        mipsIns = new MipsIns("move", 26, 4);
                        mipsInsList.add(mipsIns);
                    }
                    int temp = tempStr.charAt(0);
                    mipsIns = new MipsIns("li", 4, temp);
                    mipsInsList.add(mipsIns);
                    mipsIns = new MipsIns("li", 2, 11);
                    mipsInsList.add(mipsIns);
                    mipsIns = new MipsIns("syscall");
                    mipsInsList.add(mipsIns);
                    if (mipsFunction.register[4]) {
                        mipsIns = new MipsIns("move", 4, 26);
                        mipsInsList.add(mipsIns);
                    }
                } else {
                    if (mipsFunction.register[4]) {
                        mipsIns = new MipsIns("move", 26, 4);
                        mipsInsList.add(mipsIns);
                    }
                    mipsIns = new MipsIns("la", 4, Mips.formatStr.get(tempStr.toString()));
                    mipsInsList.add(mipsIns);
                    mipsIns = new MipsIns("li", 2, 4);
                    mipsInsList.add(mipsIns);
                    mipsIns = new MipsIns("syscall");
                    mipsInsList.add(mipsIns);
                    if (mipsFunction.register[4]) {
                        mipsIns = new MipsIns("move", 4, 26);
                        mipsInsList.add(mipsIns);
                    }
                }
                tempStr = new StringBuilder();
            }
        }
        if (ins instanceof CalculateIns calculateIns) {
            int op1Reg;
            int op2Reg;
            int des;
            switch (calculateIns.getOp()) {
                case ADD:
                    if (calculateIns.getOp1() instanceof ConstInt constInt) {
                        op2Reg = distributeReg(calculateIns.getOp2(), ins.getLocation());
                        des = distributeReg(calculateIns, ins.getLocation());
                        mipsIns = new MipsIns("addiu", des, op2Reg, constInt.getValue());
                        mipsInsList.add(mipsIns);
                    } else if (calculateIns.getOp2() instanceof ConstInt constInt) {
                        op1Reg = distributeReg(calculateIns.getOp1(), ins.getLocation());
                        des = distributeReg(calculateIns, ins.getLocation());
                        mipsIns = new MipsIns("addiu", des, op1Reg, constInt.getValue());
                        mipsInsList.add(mipsIns);
                    } else {
                        op1Reg = distributeReg(calculateIns.getOp1(), ins.getLocation());
                        op2Reg = distributeReg(calculateIns.getOp2(), ins.getLocation());
                        des = distributeReg(calculateIns, ins.getLocation());
                        mipsIns = new MipsIns("addu", des, op1Reg, op2Reg);
                        mipsInsList.add(mipsIns);
                    }
                    break;
                case SUB:
                    if (calculateIns.getOp1() instanceof ConstInt constInt) {
                        op2Reg = distributeReg(calculateIns.getOp2(), ins.getLocation());
                        des = distributeReg(calculateIns, ins.getLocation());
                        mipsIns = new MipsIns("li", 26, constInt.getValue());
                        mipsInsList.add(mipsIns);
                        mipsIns = new MipsIns("subu", des, 26, op2Reg);
                        mipsInsList.add(mipsIns);
                    } else if (calculateIns.getOp2() instanceof ConstInt constInt) {
                        op1Reg = distributeReg(calculateIns.getOp1(), ins.getLocation());
                        des = distributeReg(calculateIns, ins.getLocation());
                        mipsIns = new MipsIns("addiu", des, op1Reg, -constInt.getValue());
                        mipsInsList.add(mipsIns);
                    } else {
                        op1Reg = distributeReg(calculateIns.getOp1(), ins.getLocation());
                        op2Reg = distributeReg(calculateIns.getOp2(), ins.getLocation());
                        des = distributeReg(calculateIns, ins.getLocation());
                        mipsIns = new MipsIns("subu", des, op1Reg, op2Reg);
                        mipsInsList.add(mipsIns);
                    }
                    break;
                case MUL:
                    if (calculateIns.getOp1() instanceof ConstInt constInt) {
                        op1Reg = 26; //$k0
                        mipsIns = new MipsIns("li", 26, constInt.getValue());
                        mipsInsList.add(mipsIns);
                    } else {
                        op1Reg = distributeReg(calculateIns.getOp1(), ins.getLocation());
                    }
                    if (calculateIns.getOp2() instanceof ConstInt constInt) {
                        op2Reg = 27; //$k1
                        mipsIns = new MipsIns("li", 27, constInt.getValue());
                        mipsInsList.add(mipsIns);
                    } else {
                        op2Reg = distributeReg(calculateIns.getOp2(), ins.getLocation());
                    }
                    mipsIns = new MipsIns("mult", op1Reg, op2Reg);
                    mipsInsList.add(mipsIns);
                    des = distributeReg(calculateIns, ins.getLocation());
                    mipsIns = new MipsIns("mflo", des);
                    mipsInsList.add(mipsIns);
                    break;
                case SDIV:
                    if (calculateIns.getOp1() instanceof ConstInt constInt) {
                        op1Reg = 26; //$k0
                        mipsIns = new MipsIns("li", 26, constInt.getValue());
                        mipsInsList.add(mipsIns);
                    } else {
                        op1Reg = distributeReg(calculateIns.getOp1(), ins.getLocation());
                    }
                    if (calculateIns.getOp2() instanceof ConstInt constInt) {
                        op2Reg = 27; //$k1
                        mipsIns = new MipsIns("li", 27, constInt.getValue());
                        mipsInsList.add(mipsIns);
                    } else {
                        op2Reg = distributeReg(calculateIns.getOp2(), ins.getLocation());
                    }
                    mipsIns = new MipsIns("div", op1Reg, op2Reg);
                    mipsInsList.add(mipsIns);
                    des = distributeReg(calculateIns, ins.getLocation());
                    mipsIns = new MipsIns("mflo", des);
                    mipsInsList.add(mipsIns);
                    break;
                case SREM:
                    if (calculateIns.getOp1() instanceof ConstInt constInt) {
                        op1Reg = 26; //$k0
                        mipsIns = new MipsIns("li", 26, constInt.getValue());
                        mipsInsList.add(mipsIns);
                    } else {
                        op1Reg = distributeReg(calculateIns.getOp1(), ins.getLocation());
                    }
                    if (calculateIns.getOp2() instanceof ConstInt constInt) {
                        op2Reg = 27; //$k1
                        mipsIns = new MipsIns("li", 27, constInt.getValue());
                        mipsInsList.add(mipsIns);
                    } else {
                        op2Reg = distributeReg(calculateIns.getOp2(), ins.getLocation());
                    }
                    mipsIns = new MipsIns("div", op1Reg, op2Reg);
                    mipsInsList.add(mipsIns);
                    des = distributeReg(calculateIns, ins.getLocation());
                    mipsIns = new MipsIns("mfhi", des);
                    mipsInsList.add(mipsIns);
                    break;
            }
        } else if (ins instanceof StoreIns storeIns) {
            int v;
            int point;
            if (storeIns.getPointer() instanceof GetIns getIns) {
                point = distributeReg(storeIns.getPointer(), storeIns.getLocation());
                if (storeIns.getValue() instanceof ConstInt constInt) {
                    mipsIns = new MipsIns("li", 26, constInt.getValue());
                    mipsInsList.add(mipsIns);
                    mipsIns = new MipsIns("sw", 26, 0, point);
                    mipsInsList.add(mipsIns);
                } else {
                    v = distributeReg(storeIns.getValue(), storeIns.getLocation());
                    mipsIns = new MipsIns("sw", v, 0, point);
                    mipsInsList.add(mipsIns);
                }
            } else {
                if (storeIns.getValue() instanceof ConstInt constInt) {
                    point = distributeReg(storeIns.getPointer(), storeIns.getLocation());
                    mipsIns = new MipsIns("li", point, constInt.getValue());
                    mipsInsList.add(mipsIns);
                } else {
                    v = distributeReg(storeIns.getValue(), storeIns.getLocation());
                    point = distributeReg(storeIns.getPointer(), storeIns.getLocation());
                    mipsIns = new MipsIns("move", point, v);
                    mipsInsList.add(mipsIns);
                }
            }
        } else if (ins instanceof LoadIns loadIns) {
            if (loadIns.getPointer() instanceof GetIns getIns) {
                int point = distributeReg(getIns, loadIns.getLocation());
                int load = distributeReg(loadIns, loadIns.getLocation());
                mipsIns = new MipsIns("lw", load, 0, point);
                mipsInsList.add(mipsIns);
            } else {
                int point = distributeReg(loadIns.getPointer(), loadIns.getLocation());
                int load = distributeReg(loadIns, loadIns.getLocation());
                mipsIns = new MipsIns("move", load, point);
                mipsInsList.add(mipsIns);
            }
        } else if (ins instanceof CallIns callIns) {
            if (callIns.getFunc().getName().equals("putch")) {
                ConstInt constInt = (ConstInt) callIns.getRealParams().get(0);
                tempStr.append((char) constInt.getValue());
            } else if (callIns.getFunc().getName().equals("putint")) {
                if (mipsFunction.register[4]) {
                    mipsIns = new MipsIns("move", 26, 4);
                    mipsInsList.add(mipsIns);
                }
                Value value = callIns.getRealParams().get(0);
                if (value instanceof ConstInt constInt) {
                    mipsIns = new MipsIns("li", 4, constInt.getValue());
                    mipsInsList.add(mipsIns);
                } else {
                    mipsIns = new MipsIns("move", 4, mipsFunction.varToReg.get(value));
                    mipsInsList.add(mipsIns);
                }
                mipsIns = new MipsIns("li", 2, 1);
                mipsInsList.add(mipsIns);
                mipsIns = new MipsIns("syscall");
                mipsInsList.add(mipsIns);
                if (mipsFunction.register[4]) {
                    mipsIns = new MipsIns("move", 4, 26);
                    mipsInsList.add(mipsIns);
                }
            } else if (callIns.getFunc().getName().equals("getint")) {
                int des = distributeReg(callIns, callIns.getLocation());
                mipsIns = new MipsIns("li", 2, 5);
                mipsInsList.add(mipsIns);
                mipsIns = new MipsIns("syscall");
                mipsInsList.add(mipsIns);
                mipsIns = new MipsIns("move", des, 2);
                mipsInsList.add(mipsIns);
            } else {
                //函数调用开始，全局变量的值将不确定，加载进内存
                for (int i = 4; i < 26; i++) {
                    if (mipsFunction.register[i] &&
                        mipsFunction.regToVar[i] instanceof GlobalVariable gv) {
                        mipsIns = new MipsIns("la", 26, gv.getName());
                        mipsInsList.add(mipsIns);
                        mipsIns = new MipsIns("sw", i, 0, 26);
                        mipsInsList.add(mipsIns);
                    }
                }
                //存储当前函数的寄存器
                for (int i = 4; i < 26; i++) {
                    if (mipsFunction.register[i]) {
                        mipsIns = new MipsIns("sw", i, -mipsFunction.stackP, 30);
                        mipsInsList.add(mipsIns);
                        mipsFunction.stackP += 4;
                    }
                }
                //存储当前函数的栈帧和返回值
                if (!BB.getFather().getName().equals("main")) {
                    mipsIns = new MipsIns("sw", 31, -mipsFunction.stackP, 30);
                    mipsInsList.add(mipsIns);
                    mipsFunction.stackP += 4;
                }
                mipsIns = new MipsIns("sw", 30, -mipsFunction.stackP, 30);
                mipsInsList.add(mipsIns);
                mipsFunction.stackP += 4;
                //设置跳转函数的栈帧
                mipsIns = new MipsIns("addiu", 30, 30, -mipsFunction.stackP);
                mipsInsList.add(mipsIns);
                //函数参数传递
                for (int i = callIns.getRealParams().size() - 1; i >= 0; i--) {
                    if (callIns.getRealParams().get(i).getType() instanceof IntegerType) {
                        if (i < 4) { //小于等于4个参数
                            if (callIns.getRealParams().get(i) instanceof ConstInt constInt) {
                                mipsIns = new MipsIns("li", i + 4, constInt.getValue());
                                mipsInsList.add(mipsIns);
                            } else {
                                int regNum = distributeReg(callIns.getRealParams().get(i),
                                    callIns.getLocation());
                                mipsIns = new MipsIns("move", i + 4, regNum);
                                mipsInsList.add(mipsIns);
                            }
                        } else { //大于4个参数
                            if (callIns.getRealParams().get(i) instanceof ConstInt constInt) {
                                mipsIns = new MipsIns("li", 26, constInt.getValue());
                                mipsInsList.add(mipsIns);
                            } else {
                                int regNum = distributeReg(callIns.getRealParams().get(i),
                                    callIns.getLocation());
                                mipsIns = new MipsIns("move", 26, regNum);
                                mipsInsList.add(mipsIns);
                            }
                            mipsIns = new MipsIns("sw", 26,
                                -(callIns.getRealParams().size() - 1 - i) * 4, 30);
                            mipsInsList.add(mipsIns);
                        }
                    } else { //数组地址传递
                        if (i < 4) {
                            int regNum = distributeReg(callIns.getRealParams().get(i),
                                callIns.getLocation());
                            mipsIns = new MipsIns("move", i + 4, regNum);
                            mipsInsList.add(mipsIns);
                        } else {
                            int regNum = distributeReg(callIns.getRealParams().get(i),
                                callIns.getLocation());
                            mipsIns = new MipsIns("sw", regNum,
                                -(callIns.getRealParams().size() - 1 - i) * 4, 30);
                            mipsInsList.add(mipsIns);
                        }
                    }
                }
                //跳转
                mipsIns = new MipsIns("jal", callIns.getFunc().getName());
                mipsInsList.add(mipsIns);
                //恢复现场
                mipsFunction.stackP -= 4;
                mipsIns = new MipsIns("lw", 30, 4, 30);
                mipsInsList.add(mipsIns);
                if (!BB.getFather().getName().equals("main")) {
                    mipsFunction.stackP -= 4;
                    mipsIns = new MipsIns("lw", 31, -mipsFunction.stackP, 30);
                    mipsInsList.add(mipsIns);
                }
                for (int i = 25; i >= 4; i--) {
                    if (mipsFunction.register[i]) {
                        mipsFunction.stackP -= 4;
                        mipsIns = new MipsIns("lw", i, -mipsFunction.stackP, 30);
                        mipsInsList.add(mipsIns);
                    }
                }
                if (callIns.getFunc().getRetType() instanceof IntegerType) {
                    int regNum = distributeReg(callIns, callIns.getLocation());
                    mipsIns = new MipsIns("move", regNum, 2);
                    mipsInsList.add(mipsIns);
                }
                //函数调用结束，全局变量的值将不确定，重新加载
                for (int i = 4; i < 26; i++) {
                    if (mipsFunction.register[i] &&
                        mipsFunction.regToVar[i] instanceof GlobalVariable gv) {
                        mipsIns = new MipsIns("la", 26, gv.getName());
                        mipsInsList.add(mipsIns);
                        mipsIns = new MipsIns("lw", i, 0, 26);
                        mipsInsList.add(mipsIns);
                    }
                }
            }
        } else if (ins instanceof RetIns retIns) {
            if (BB.getFather().getName().equals("main")) {
                mipsIns = new MipsIns("li", 2, 10);
                mipsInsList.add(mipsIns);
                mipsIns = new MipsIns("syscall");
                mipsInsList.add(mipsIns);
            } else if (retIns.getValue() != null) {
                if (retIns.getValue() instanceof ConstInt constInt) {
                    mipsIns = new MipsIns("li", 2, constInt.getValue());
                    mipsInsList.add(mipsIns);
                } else {
                    mipsIns = new MipsIns("move", 2, mipsFunction.varToReg.get(retIns.getValue()));
                    mipsInsList.add(mipsIns);
                }
                allRegToStack();
                mipsIns = new MipsIns("jr", 31);
                mipsInsList.add(mipsIns);
            } else {
                allRegToStack();
                mipsIns = new MipsIns("jr", 31);
                mipsInsList.add(mipsIns);
            }
        } else if (ins instanceof BrIns brIns) {
            if (brIns.getDest() != null) {
                localRegToStack();
                if (!brIns.getDest().equals(nextBB)) {
                    mipsIns = new MipsIns("j", brIns.getDest().getName());
                    mipsInsList.add(mipsIns);
                }
            } else {
                int regNum = distributeReg(brIns.getCond(), brIns.getLocation());
                mipsIns = new MipsIns("move", 27, regNum);
                mipsInsList.add(mipsIns);
                localRegToStack();
                if (brIns.getTrueBB().equals(nextBB)) {
                    mipsIns = new MipsIns("beq", 0, 27, brIns.getFalseBB().getName());
                    mipsInsList.add(mipsIns);
                } else if (brIns.getFalseBB().equals(nextBB)) {
                    mipsIns = new MipsIns("bne", 0, 27, brIns.getTrueBB().getName());
                    mipsInsList.add(mipsIns);
                } else {
                    mipsIns = new MipsIns("bne", 0, 27, brIns.getTrueBB().getName());
                    mipsInsList.add(mipsIns);
                    mipsIns = new MipsIns("j", brIns.getFalseBB().getName());
                    mipsInsList.add(mipsIns);
                }
            }
        } else if (ins instanceof CmpIns cmpIns) {
            int reg1;
            int reg2;
            if (cmpIns.getOp1() instanceof ConstInt constInt) {
                if (constInt.getValue() == 0) {
                    reg1 = 0;
                } else {
                    reg1 = 26;
                    mipsIns = new MipsIns("li", reg1, constInt.getValue());
                    mipsInsList.add(mipsIns);
                }
            } else {
                reg1 = distributeReg(cmpIns.getOp1(), cmpIns.getLocation());
            }
            if (cmpIns.getOp2() instanceof ConstInt constInt) {
                if (constInt.getValue() == 0) {
                    reg2 = 0;
                } else {
                    reg2 = 27;
                    mipsIns = new MipsIns("li", reg2, constInt.getValue());
                    mipsInsList.add(mipsIns);
                }
            } else {
                reg2 = distributeReg(cmpIns.getOp2(), cmpIns.getLocation());
            }
            int res = distributeReg(cmpIns, cmpIns.getLocation());
            switch (cmpIns.getCond()) {
                case "eq":
                    mipsIns = new MipsIns("seq", res, reg1, reg2);
                    mipsInsList.add(mipsIns);
                    break;
                case "ne":
                    mipsIns = new MipsIns("sne", res, reg1, reg2);
                    mipsInsList.add(mipsIns);
                    break;
                case "slt":
                    mipsIns = new MipsIns("slt", res, reg1, reg2);
                    mipsInsList.add(mipsIns);
                    break;
                case "sgt":
                    mipsIns = new MipsIns("sgt", res, reg1, reg2);
                    mipsInsList.add(mipsIns);
                    break;
                case "sle":
                    mipsIns = new MipsIns("sle", res, reg1, reg2);
                    mipsInsList.add(mipsIns);
                    break;
                case "sge":
                    mipsIns = new MipsIns("sge", res, reg1, reg2);
                    mipsInsList.add(mipsIns);
                    break;
            }
        } else if (ins instanceof UnaryIns unaryIns) {
            mipsFunction.varToReg.put(ins, mipsFunction.varToReg.get(unaryIns.getValue()));
        } else if (ins instanceof AllocaIns allocaIns) {
            if (allocaIns.getDefType() instanceof IntegerType) { //普通变量
                mipsFunction.stack.put(ins, mipsFunction.stackP);
                mipsFunction.stackP += 4;
            } else if (allocaIns.getDefType() instanceof ArrayType arrayType) { //局部数组
                mipsFunction.stackP += 4 * (arrayType.getMaxSize() - 1); //保存数组的空间
                mipsIns = new MipsIns("addiu", 26, 30, -mipsFunction.stackP); //计算数组首元素的地址
                mipsInsList.add(mipsIns);
                mipsFunction.stackP += 4;
                mipsIns = new MipsIns("sw", 26, -mipsFunction.stackP, 30);
                mipsInsList.add(mipsIns);
                mipsFunction.stack.put(ins, mipsFunction.stackP); //保存数组首元素的地址
                mipsFunction.stackP += 4;
            }
        } else if (ins instanceof GetIns getIns) {
            int regNum = distributeReg(getIns.getValue(), ins.getLocation());
            int resReg = distributeReg(getIns, ins.getLocation());
            Value first = getIns.getIndex().get(0);
            PointerType pointer = (PointerType) getIns.getValue().getType();
            if (first instanceof ConstInt constInt) {
                int temp = pointer.getPointType().getSize() * constInt.getValue();
                mipsIns = new MipsIns("addiu", resReg, regNum, temp);
                mipsInsList.add(mipsIns);
            } else {
                int temp = distributeReg(first, ins.getLocation());
                mipsIns = new MipsIns("li", 26, pointer.getPointType().getSize());
                mipsInsList.add(mipsIns);
                mipsIns = new MipsIns("mult", 26, temp);
                mipsInsList.add(mipsIns);
                mipsIns = new MipsIns("mflo", 26);
                mipsInsList.add(mipsIns);
                mipsIns = new MipsIns("addu", resReg, regNum, 26);
                mipsInsList.add(mipsIns);
            }
            boolean flag = false;
            if (pointer.getPointType() instanceof ArrayType arrayType) {
                int temp = 0;
                for (int i = 1; i < getIns.getIndex().size(); i++) {
                    if (getIns.getIndex().get(i) instanceof ConstInt constInt) {
                        flag = true;
                        temp += constInt.getValue() * arrayType.getP(i - 1) * 4;
                    } else {
                        if (flag) {
                            mipsIns = new MipsIns("addiu", resReg, resReg, temp);
                            mipsInsList.add(mipsIns);
                            flag = false;
                        }
                        temp = distributeReg(getIns.getIndex().get(i), ins.getLocation());
                        mipsIns = new MipsIns("li", 26, arrayType.getP(i - 1) * 4);
                        mipsInsList.add(mipsIns);
                        mipsIns = new MipsIns("mult", 26, temp);
                        mipsInsList.add(mipsIns);
                        mipsIns = new MipsIns("mflo", 26);
                        mipsInsList.add(mipsIns);
                        mipsIns = new MipsIns("addu", resReg, resReg, 26);
                        mipsInsList.add(mipsIns);
                        temp = 0;
                    }
                }
                if (flag) {
                    mipsIns = new MipsIns("addiu", resReg, resReg, temp);
                    mipsInsList.add(mipsIns);
                }
            }
        }
    }

    private int distributeReg(Value value, int location) {
        if (mipsFunction.varToReg.containsKey(value)) {
            int regNum = mipsFunction.varToReg.get(value);
            mipsFunction.register[regNum] = true;
            mipsFunction.regToVar[regNum] = value;
            return regNum;
        }
        int regNum = searchRestLocalReg();
        MipsIns mipsIns;
        if (regNum != -1) {
            mipsFunction.register[regNum] = true;
            mipsFunction.regToVar[regNum] = value;
            mipsFunction.varToReg.put(value, regNum);
        } else {
            int latestUse = 8;
            int latest = -1;
            for (int i = 8; i < 26; i++) {
                if (i == 16) {
                    i = 24;
                }
                if (mipsFunction.regToVar[i].getNextUse(BB, location) < 0) {
                    if (mipsFunction.regToVar[i] instanceof AllocaIns ||
                        mipsFunction.regToVar[i] instanceof GlobalValue ||
                        mipsFunction.regToVar[i] instanceof GetIns) {
                        latestUse = i;
                        latest = 0; //需要写回内存
                        break;
                    }
                    latestUse = i;
                    latest = -1;
                    break;
                }
                if (mipsFunction.regToVar[i].getNextUse(BB, location) > latest) {
                    latestUse = i;
                    latest = mipsFunction.regToVar[i].getNextUse(BB, location);
                }
            }
            //清除上个引用
            ArrayList<Value> temp = new ArrayList<>();
            for (Value v : mipsFunction.varToReg.keySet()) {
                if (mipsFunction.varToReg.get(v) == latestUse) {
                    temp.add(v);
                }
            }
            for (int j = 0; j < temp.size(); j++) {
                if (latest >= 0) {     //需要写回内存
                    if (temp.get(j) instanceof GlobalVariable gv) { //全局变量
                        mipsIns = new MipsIns("la", 26, gv.getName());
                        mipsInsList.add(mipsIns);
                        mipsIns = new MipsIns("sw", latestUse, 0, 26);
                        mipsInsList.add(mipsIns);
                    } else {
                        int offset;
                        if (mipsFunction.stack.containsKey(temp.get(j))) {
                            offset = -mipsFunction.stack.get(temp.get(j));
                        } else {
                            offset = -mipsFunction.stackP;
                            mipsFunction.stack.put(temp.get(j), mipsFunction.stackP);
                            mipsFunction.stackP += 4;
                        }
                        mipsIns = new MipsIns("sw", latestUse, offset, 30);
                        mipsInsList.add(mipsIns);
                    }
                }
                mipsFunction.varToReg.remove(temp.get(j));
            }
            mipsFunction.register[latestUse] = true;
            mipsFunction.regToVar[latestUse] = value;
            mipsFunction.varToReg.put(value, latestUse);
            regNum = latestUse;
        }
        if (value instanceof CalculateIns || value instanceof GetIns) {
            Instruction ins = (Instruction) value;
            if (ins.getLocation() == location) {
                return regNum;
            }
        }
        if (BB.getInstructions().get(location) instanceof StoreIns storeIns &&
            value.equals(storeIns.getPointer())) {
            return regNum;
        }
        if (value instanceof GlobalVariable gv) {
            mipsIns = new MipsIns("la", 26, gv.getName());
            mipsInsList.add(mipsIns);
            mipsIns = new MipsIns("lw", regNum, 0, 26);
            mipsInsList.add(mipsIns);
        } else if (value instanceof GlobalArray globalArray) {
            mipsIns = new MipsIns("la", regNum, globalArray.getName());
            mipsInsList.add(mipsIns);
        } else if (value instanceof GetIns getIns) {
            //将内存中数组地址加载进寄存器
            mipsIns = new MipsIns("lw", regNum, -mipsFunction.stack.get(getIns), 30);
            mipsInsList.add(mipsIns);
        } else { //局部变量，包括局部数组
            if (mipsFunction.stack.containsKey(value)) {
                mipsIns = new MipsIns("lw", regNum, -mipsFunction.stack.get(value), 30);
                mipsInsList.add(mipsIns);
            }
        }
        return regNum;
    }

    private int searchRestLocalReg() {
        for (int i = 8; i < 16; i++) {
            if (!mipsFunction.register[i]) {
                return i;
            }
        }
        if (!mipsFunction.register[24]) {
            return 24;
        }
        if (!mipsFunction.register[25]) {
            return 25;
        }
        return -1;
    }

    private void localRegToStack() {
        MipsIns mipsIns;
        for (int i = 8; i < 26; i++) {
            if (i == 16) {
                i = 24;
            }
            if (!mipsFunction.register[i]) {
                continue;
            }
            mipsFunction.register[i] = false;
            ArrayList<Value> temp = new ArrayList<>();
            for (Value value : mipsFunction.varToReg.keySet()) {
                if (mipsFunction.varToReg.get(value) == i) {
                    temp.add(value);
                }
            }
            for (int j = 0; j < temp.size(); j++) {
                if (temp.get(j) instanceof AllocaIns allocaIns) {
                    if (mipsFunction.stack.containsKey(allocaIns)) {
                        mipsIns = new MipsIns("sw", i, -mipsFunction.stack.get(allocaIns), 30);
                        mipsInsList.add(mipsIns);
                    } else {
                        mipsIns = new MipsIns("sw", i, -mipsFunction.stackP, 30);
                        mipsInsList.add(mipsIns);
                        mipsFunction.stackP += 4;
                    }
                } else if (temp.get(j) instanceof GlobalVariable gv) {
                    mipsIns = new MipsIns("la", 26, gv.getName());
                    mipsInsList.add(mipsIns);
                    mipsIns = new MipsIns("sw", i, 0, 26);
                    mipsInsList.add(mipsIns);
                }
                mipsFunction.varToReg.remove(temp.get(j));
            }
        }
    }

    private void allRegToStack() {
        MipsIns mipsIns;
        for (int i = 8; i < 26; i++) {
            if (!mipsFunction.register[i]) {
                continue;
            }
            mipsFunction.register[i] = false;
            ArrayList<Value> temp = new ArrayList<>();
            for (Value value : mipsFunction.varToReg.keySet()) {
                if (mipsFunction.varToReg.get(value) == i) {
                    temp.add(value);
                }
            }
            for (int j = 0; j < temp.size(); j++) {
                if (temp.get(j) instanceof GlobalVariable gv) {
                    mipsIns = new MipsIns("la", 26, gv.getName());
                    mipsInsList.add(mipsIns);
                    mipsIns = new MipsIns("sw", i, 0, 26);
                    mipsInsList.add(mipsIns);
                }
            }
        }
    }

    @Override
    public String toString() {
        for (int i = 0; i < mipsInsList.size(); i++) {
            sb.append(mipsInsList.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}
