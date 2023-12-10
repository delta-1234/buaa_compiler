package llvm.value;

import llvm.Use;
import llvm.type.IntegerType;
import llvm.type.Type;
import llvm.value.constant.ConstInt;
import llvm.value.constant.IRFunction;
import llvm.value.instruction.AllocaIns;
import llvm.value.instruction.BrIns;
import llvm.value.instruction.CalculateIns;
import llvm.value.instruction.CallIns;
import llvm.value.instruction.CmpIns;
import llvm.value.instruction.GetIns;
import llvm.value.instruction.Instruction;
import llvm.value.instruction.LoadIns;
import llvm.value.instruction.Phi;
import llvm.value.instruction.RetIns;
import llvm.value.instruction.StoreIns;
import llvm.value.instruction.UnaryIns;

import java.util.ArrayList;
import java.util.HashSet;

public class BasicBlock extends Value {
    private ArrayList<Instruction> instructions;
    private IRFunction father;
    private static int count = 0;
    private HashSet<Value> defValue;
    private HashSet<Value> useValue;
    private ArrayList<BasicBlock> succeed;
    private HashSet<Value> activateVar;
    private HashSet<BasicBlock> dom;
    private BasicBlock immediateDom;
    private HashSet<BasicBlock> DF; //支配边界
    private HashSet<Value> defined;

    public BasicBlock(String name, Type type, IRFunction father) {
        super(name, type);
        instructions = new ArrayList<>();
        this.father = father;
        setIdent("%B" + count);
        setName("B" + count);
        count++;
        defValue = new HashSet<>();
        useValue = new HashSet<>();
        succeed = new ArrayList<>();
        activateVar = new HashSet<>();
        dom = new HashSet<>();
        immediateDom = null;
        DF = new HashSet<>();
        defined = new HashSet<>();
    }

    public void addIns(Instruction ins) {
        instructions.add(ins);
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public Instruction getLastIns() {
        if (instructions.size() == 0) {
            return null;
        }
        return instructions.get(instructions.size() - 1);
    }

    public void deleteDead() {
        boolean flag = false;
        for (int i = 0; i < instructions.size(); i++) {
            if (flag) {
                instructions.get(i).destroy();
                instructions.remove(i);
                i--;
            }
            if (instructions.get(i) instanceof RetIns ||
                instructions.get(i) instanceof BrIns) {
                flag = true;
            }
        }
    }

    public boolean deleteUseless() {
        boolean flag = false;
        int begin = 0;
        if (father.getBasicBlocks().get(0).equals(this)) {
            begin = father.getParamNum() * 2;
        }
        for (int i = begin; i < instructions.size(); i++) {
            if (instructions.get(i) instanceof AllocaIns ||
                instructions.get(i) instanceof CalculateIns ||
                instructions.get(i) instanceof CmpIns ||
                instructions.get(i) instanceof LoadIns ||
                instructions.get(i) instanceof UnaryIns ||
                instructions.get(i) instanceof Phi) {
                if (instructions.get(i).getUseList().size() == 0) {
                    instructions.get(i).destroy();
                    instructions.remove(i);
                    i--;
                    flag = true;
                }
            } else if (instructions.get(i) instanceof StoreIns storeIns &&
                storeIns.getPointer().getUseList().size() == 0 &&
                !(storeIns.getPointer() instanceof GetIns)) {
                storeIns.destroy();
                instructions.remove(i);
                i--;
                flag = true;
            } else if (instructions.get(i) instanceof GetIns getIns) {
                if (getIns.getUseList().size() == 0) {
                    boolean f = false;
                    for (Use u : getIns.getOperands()) {
                        if (u.getValue() instanceof StoreIns) {
                            f = true;
                        }
                    }
                    if (!f) {
                        getIns.destroy();
                        instructions.remove(i);
                        i--;
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    public void calUseDef() {
        int begin = 0;
        if (father.getBasicBlocks().get(0).equals(this)) {
            begin = Math.min(father.getParamNum(), 4) * 2;
        }
        for (int i = begin; i < instructions.size(); i++) {
            if (instructions.get(i) instanceof StoreIns storeIns) {
                defined.add(storeIns.getPointer());
            } else if (instructions.get(i) instanceof LoadIns loadIns) {
                defined.add(loadIns);
            }
            if (instructions.get(i) instanceof LoadIns loadIns &&
                !defValue.contains(loadIns.getPointer()) &&
                loadIns.getPointer() instanceof AllocaIns allocaIns) {
                if (allocaIns.isArg()) {
                    continue;
                }
                useValue.add(loadIns.getPointer());
                if (!useValue.contains(loadIns)) {
                    defValue.add(loadIns);
                }
            } else if (instructions.get(i) instanceof StoreIns storeIns &&
                !useValue.contains(storeIns.getPointer()) &&
                storeIns.getPointer() instanceof AllocaIns allocaIns) {
                if (allocaIns.isArg()) {
                    continue;
                }
                defValue.add(storeIns.getPointer());
                if (!(storeIns.getValue() instanceof ConstInt) &&
                    !defValue.contains(storeIns.getValue())) {
                    useValue.add(storeIns.getValue());
                }
            } else if (instructions.get(i) instanceof CalculateIns calculateIns) {
                defined.add(calculateIns);
                if (!(calculateIns.getOp1() instanceof ConstInt) &&
                    !defValue.contains(calculateIns.getOp1())) {
                    useValue.add(calculateIns.getOp1());
                }
                if (!(calculateIns.getOp2() instanceof ConstInt) &&
                    !defValue.contains(calculateIns.getOp2())) {
                    useValue.add(calculateIns.getOp2());
                }
                if (!useValue.contains(calculateIns)) {
                    defValue.add(calculateIns);
                }
            } else if (instructions.get(i) instanceof BrIns brIns) {
                if (brIns.getDest() == null) {
                    if (!(brIns.getCond() instanceof ConstInt) &&
                        !defValue.contains(brIns.getCond())) {
                        useValue.add(brIns.getCond());
                    }
                }
            } else if (instructions.get(i) instanceof CallIns callIns) {
                for (Value v : callIns.getRealParams()) {
                    if (!(v instanceof ConstInt) && !defValue.contains(v)) {
                        useValue.add(v);
                    }
                }
                if (callIns.getFunc().getRetType() instanceof IntegerType) {
                    defined.add(callIns);
                }
                if (callIns.getFunc().getRetType() instanceof IntegerType && !useValue.contains(callIns)) {
                    defValue.add(callIns);
                }
            } else if (instructions.get(i) instanceof CmpIns cmpIns) {
                defined.add(cmpIns);
                if (!(cmpIns.getOp1() instanceof ConstInt) && !defValue.contains(cmpIns.getOp1())) {
                    useValue.add(cmpIns.getOp1());
                }
                if (!(cmpIns.getOp2() instanceof ConstInt) && !defValue.contains(cmpIns.getOp2())) {
                    useValue.add(cmpIns.getOp2());
                }
                if (!useValue.contains(cmpIns)) {
                    defValue.add(cmpIns);
                }
            } else if (instructions.get(i) instanceof RetIns retIns) {
                if (retIns.getValue() != null && !(retIns.getValue() instanceof ConstInt) &&
                    !defValue.contains(retIns.getValue())) {
                    useValue.add(retIns.getValue());
                }
            }
        }
    }

    public void removeUnaryIns() {
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i) instanceof UnaryIns unaryIns) {
                unaryIns.replaceUsed(unaryIns.getValue());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n" + getName() + ":\n");
        for (int i = 0; i < instructions.size(); i++) {
            sb.append("    ");
            sb.append(instructions.get(i));
        }
        return sb.toString();
    }

    public IRFunction getFather() {
        return father;
    }

    public ArrayList<BasicBlock> getSucceed() {
        return succeed;
    }

    public HashSet<Value> getDefValue() {
        return defValue;
    }

    public HashSet<Value> getUseValue() {
        return useValue;
    }

    public HashSet<Value> getActivateVar() {
        return activateVar;
    }

    public HashSet<BasicBlock> getDom() {
        return dom;
    }

    public boolean isStrictDom(BasicBlock BB) {
        if (dom.contains(BB) && !BB.equals(this)) {
            return true;
        }
        return false;
    }

    public BasicBlock getImmediateDom() {
        return immediateDom;
    }

    public void setImmediateDom(BasicBlock immediateDom) {
        this.immediateDom = immediateDom;
    }

    public HashSet<BasicBlock> getDF() {
        return DF;
    }

    public void addEntry(Instruction ins) {
        instructions.add(0, ins);
    }

    public ArrayList<Phi> getPhi() {
        ArrayList<Phi> phis = new ArrayList<>();
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i) instanceof Phi) {
                phis.add((Phi) instructions.get(i));
            }
        }
        return phis;
    }

    public void addEnd(Instruction ins) {
        instructions.add(instructions.size() - 1, ins);
    }

    public HashSet<Value> getDefined() {
        return defined;
    }

    public boolean calSimplify() {
        boolean flag = false;
        for (int i = 0; i < instructions.size(); i++) {
            Instruction ins = instructions.get(i);
            if (ins instanceof BrIns brIns) {
                if (brIns.getDest() == null && brIns.getCond() instanceof ConstInt constInt) {
                    if (constInt.getValue() != 0) {
                        brIns.setDest(brIns.getTrueBB());
                    } else {
                        brIns.setDest(brIns.getFalseBB());
                    }
                    brIns.removeValue();
                    flag = true;
                }
            } else if (ins instanceof CalculateIns calculateIns) {
                if (!(calculateIns.getOp1() instanceof ConstInt constInt1)) {
                    continue;
                }
                if (!(calculateIns.getOp2() instanceof ConstInt constInt2)) {
                    continue;
                }
                flag = true;
                int res;
                switch (calculateIns.getOp()) {
                    case ADD -> res = constInt1.getValue() + constInt2.getValue();
                    case SUB -> res = constInt1.getValue() - constInt2.getValue();
                    case MUL -> res = constInt1.getValue() * constInt2.getValue();
                    case SDIV -> res = constInt1.getValue() / constInt2.getValue();
                    case SREM -> res = constInt1.getValue() % constInt2.getValue();
                    default -> res = 0;
                }
                ConstInt constInt = new ConstInt(String.valueOf(res), new IntegerType(32), res);
                calculateIns.replaceUsed(constInt);
                calculateIns.destroy();
                instructions.remove(calculateIns);
                i--;
            } else if (ins instanceof CmpIns cmpIns) {
                if (!(cmpIns.getOp1() instanceof ConstInt constInt1)) {
                    continue;
                }
                if (!(cmpIns.getOp2() instanceof ConstInt constInt2)) {
                    continue;
                }
                int res;
                flag = true;
                switch (cmpIns.getCond()) {
                    case "eq" -> res = (constInt1.getValue() == constInt2.getValue()) ? 1 : 0;
                    case "ne" -> res = (constInt1.getValue() != constInt2.getValue()) ? 1 : 0;
                    case "sgt" -> res = (constInt1.getValue() > constInt2.getValue()) ? 1 : 0;
                    case "sge" -> res = (constInt1.getValue() >= constInt2.getValue()) ? 1 : 0;
                    case "slt" -> res = (constInt1.getValue() < constInt2.getValue()) ? 1 : 0;
                    case "sle" -> res = (constInt1.getValue() <= constInt2.getValue()) ? 1 : 0;
                    default -> res = 0;
                }
                ConstInt constInt = new ConstInt(String.valueOf(res), new IntegerType(1), res);
                cmpIns.replaceUsed(constInt);
                cmpIns.destroy();
                instructions.remove(cmpIns);
                i--;
            } else if (ins instanceof UnaryIns unaryIns) {
                if (unaryIns.getValue() instanceof ConstInt constInt) {
                    flag = true;
                    ConstInt res = new ConstInt(String.valueOf(constInt.getValue()),
                        new IntegerType(32), constInt.getValue());
                    unaryIns.replaceUsed(res);
                    unaryIns.destroy();
                    instructions.remove(unaryIns);
                    i--;
                }
            }
        }
        return flag;
    }
}
