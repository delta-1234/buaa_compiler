package util;

import llvm.IRModule;
import llvm.type.ArrayType;
import llvm.type.FunctionType;
import llvm.type.IntegerType;
import llvm.type.PointerType;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.Value;
import llvm.value.constant.ConstInt;
import llvm.value.constant.GlobalArray;
import llvm.value.constant.GlobalVariable;
import llvm.value.constant.IRFunction;
import llvm.value.instruction.AllocaIns;
import llvm.value.instruction.BrIns;
import llvm.value.instruction.CalculateIns;
import llvm.value.instruction.CallIns;
import llvm.value.instruction.CmpIns;
import llvm.value.instruction.GetIns;
import llvm.value.instruction.Instruction;
import llvm.value.instruction.LoadIns;
import llvm.value.instruction.Operation;
import llvm.value.instruction.UnaryIns;
import front.parser.Exp.AddExp;
import front.parser.Exp.EqExp;
import front.parser.Exp.LAndExp;
import front.parser.Exp.LOrExp;
import front.parser.Exp.MulExp;
import front.parser.Exp.PrimaryExp;
import front.parser.Exp.RelExp;
import front.parser.Exp.UnaryExp;
import front.parser.Stmt.LVal;

import java.util.ArrayList;

public class Calculator {
    private static IRModule module = IRBuilder.module;
    public static Value calOr(LOrExp lOrExp, BasicBlock BB, BasicBlock trueIf, BasicBlock falseIf) {
        BasicBlock last = BB;
        Value value;
        Instruction ins;
        IRFunction func = BB.getFather();
        for (int i = 0; i < lOrExp.getLAndExps().size() - 1; i++) {
            BasicBlock next = new BasicBlock("", null, func);
            value = calAnd(lOrExp.getLAndExps().get(i), last, trueIf, next);
            if (value instanceof ConstInt) {
                if (((ConstInt) value).getValue() == 1) {
                    ins = new BrIns("", null, func.getLastBB(), Operation.BR, trueIf);
                } else {
                    ins = new BrIns("", null, func.getLastBB(), Operation.BR, next);
                }
            } else {
                ins = new BrIns("", null, func.getLastBB(), Operation.BR,
                    value, trueIf, next);
            }
            func.getLastBB().addIns(ins);
            func.addBB(next);
            last = next;
        }
        value = calAnd(lOrExp.getLAndExps().get(lOrExp.getLAndExps().size() - 1), last, trueIf,
            falseIf);
        return value;
    }

    public static Value calAnd(LAndExp lAndExp, BasicBlock BB, BasicBlock trueIf, BasicBlock falseIf) {
        BasicBlock last = BB;
        Value value;
        Instruction ins;
        IRFunction func = BB.getFather();
        for (int i = 0; i < lAndExp.getEqExps().size() - 1; i++) {
            BasicBlock next = new BasicBlock("", null, func);
            value = calEq(lAndExp.getEqExps().get(i), last);
            if (value instanceof ConstInt) {
                if (((ConstInt) value).getValue() == 1) {
                    ins = new BrIns("", null, func.getLastBB(), Operation.BR, next);
                } else {
                    ins = new BrIns("", null, func.getLastBB(), Operation.BR, falseIf);
                }
            } else {
                ins = new BrIns("", null, func.getLastBB(), Operation.BR,
                    value, next, falseIf);
            }
            func.getLastBB().addIns(ins);
            func.addBB(next);
            last = next;
        }
        value =
            calEq(lAndExp.getEqExps().get(lAndExp.getEqExps().size() - 1), last);
        return value;
    }

    public static Value calEq(EqExp eqExp, BasicBlock BB) {
        Value op1 = calRel(eqExp.getRelExps().get(0), BB);
        Value op2;
        Instruction ins = null;
        ConstInt one = new ConstInt("1", new IntegerType(1), 1);
        ConstInt zero = new ConstInt("0", new IntegerType(1), 0);
        if (eqExp.getIsEqual().size() == 0) {
            if (op1 instanceof ConstInt) {
                if (((ConstInt) op1).getValue() != 0) {
                    return one;
                } else {
                    return zero;
                }
            }
            ins = new CmpIns("", new IntegerType(1), BB, Operation.ICMP, "ne",
                op1, ConstInt.getZero());
            BB.addIns(ins);
            return ins;
        }
        for (int i = 1; i < eqExp.getRelExps().size(); i++) {
            op2 = calRel(eqExp.getRelExps().get(i), BB);
            if (eqExp.getIsEqual().get(i - 1)) {
                if (op1 instanceof ConstInt && op2 instanceof ConstInt) {
                    if (((ConstInt) op1).getValue() == ((ConstInt) op2).getValue()) {
                        op1 = one;
                    } else {
                        op1 = zero;
                    }
                    continue;
                }
                ins = new CmpIns("", new IntegerType(1), BB, Operation.ICMP,
                    "eq", op1, op2);
                BB.addIns(ins);
            } else {
                if (op1 instanceof ConstInt && op2 instanceof ConstInt) {
                    if (((ConstInt) op1).getValue() == ((ConstInt) op2).getValue()) {
                        op1 = one;
                    } else {
                        op1 = zero;
                    }
                    continue;
                }
                ins = new CmpIns("", new IntegerType(1), BB, Operation.ICMP, "ne", op1, op2);
                BB.addIns(ins);
            }
            if (i != eqExp.getRelExps().size() - 1) {
                ins = new UnaryIns("", new IntegerType(32), BB, Operation.ZEXT, ins);
                BB.addIns(ins);
            }
            op1 = ins;
        }
        return op1;
    }

    public static Value calRel(RelExp relExp, BasicBlock BB) {
        Value op1 = calAddExp(relExp.getAddExps().get(0), BB);
        Value op2;
        Instruction ins = null;
        ConstInt one = new ConstInt("1", new IntegerType(32), 1);
        if (relExp.getOp().size() == 0) {
            return op1;
        }
        for (int i = 1; i < relExp.getAddExps().size(); i++) {
            op2 = calAddExp(relExp.getAddExps().get(i), BB);
            if (relExp.getOp().get(i - 1) == 1) {
                if (op1 instanceof ConstInt && op2 instanceof ConstInt) {
                    if (((ConstInt) op1).getValue() < ((ConstInt) op2).getValue()) {
                        op1 = one;
                    } else {
                        op1 = ConstInt.getZero();
                    }
                    continue;
                }
                ins = new CmpIns("", new IntegerType(1), BB, Operation.ICMP,
                    "slt", op1, op2);
                BB.addIns(ins);
                ins = new UnaryIns("", new IntegerType(32), BB, Operation.ZEXT, ins);
                BB.addIns(ins);
            } else if (relExp.getOp().get(i - 1) == 2) {
                if (op1 instanceof ConstInt && op2 instanceof ConstInt) {
                    if (((ConstInt) op1).getValue() > ((ConstInt) op2).getValue()) {
                        op1 = one;
                    } else {
                        op1 = ConstInt.getZero();
                    }
                    continue;
                }
                ins = new CmpIns("", new IntegerType(1), BB, Operation.ICMP,
                    "sgt", op1, op2);
                BB.addIns(ins);
                ins = new UnaryIns("", new IntegerType(32), BB, Operation.ZEXT, ins);
                BB.addIns(ins);
            } else if (relExp.getOp().get(i - 1) == 3) {
                if (op1 instanceof ConstInt && op2 instanceof ConstInt) {
                    if (((ConstInt) op1).getValue() <= ((ConstInt) op2).getValue()) {
                        op1 = one;
                    } else {
                        op1 = ConstInt.getZero();
                    }
                    continue;
                }
                ins = new CmpIns("", new IntegerType(1), BB, Operation.ICMP,
                    "sle", op1, op2);
                BB.addIns(ins);
                ins = new UnaryIns("", new IntegerType(32), BB, Operation.ZEXT, ins);
                BB.addIns(ins);
            } else {
                if (op1 instanceof ConstInt && op2 instanceof ConstInt) {
                    if (((ConstInt) op1).getValue() >= ((ConstInt) op2).getValue()) {
                        op1 = one;
                    } else {
                        op1 = ConstInt.getZero();
                    }
                    continue;
                }
                ins = new CmpIns("", new IntegerType(1), BB, Operation.ICMP,
                    "sge", op1, op2);
                BB.addIns(ins);
                ins = new UnaryIns("", new IntegerType(32), BB, Operation.ZEXT, ins);
                BB.addIns(ins);
            }
            op1 = ins;
        }
        return op1;
    }
    public static Value calAddExp(AddExp addExp, BasicBlock BB) {
        addExp.sort(module);
        Value res = calMulExp(addExp.getMulExp(0), BB);
        if (res instanceof ConstInt && addExp.isNeg(0)) {
            res = ((ConstInt) res).neg();
        }
        for (int i = 1; i < addExp.getMulNum(); i++) {
            Value temp = calMulExp(addExp.getMulExp(i), BB);
            if (res instanceof ConstInt && temp instanceof ConstInt) {
                if (addExp.isNeg(i)) {
                    res = ((ConstInt) res).sub((ConstInt) temp);
                } else {
                    res = ((ConstInt) res).add((ConstInt) temp);
                }
                continue;
            }
            if (addExp.isNeg(i)) {
                res = new CalculateIns("", temp.getType(), BB, Operation.SUB, res, temp);
            } else {
                res = new CalculateIns("", temp.getType(), BB, Operation.ADD, res, temp);
            }
            BB.addIns((Instruction) res);
        }
        return res;
    }

    public static Value calMulExp(MulExp mulExp, BasicBlock BB) {
        Value res = calUnaryExp(mulExp.getUnaryExp(0), BB);
        for (int i = 1; i < mulExp.getUnaryNum(); i++) {
            Value temp = calUnaryExp(mulExp.getUnaryExp(i), BB);
            if (res instanceof ConstInt && temp instanceof ConstInt) {
                if (mulExp.getOp(i) == 1) {
                    res = ((ConstInt) res).mul((ConstInt) temp);
                } else if (mulExp.getOp(i) == 2) {
                    res = ((ConstInt) res).mod((ConstInt) temp);
                } else {
                    res = ((ConstInt) res).div((ConstInt) temp);
                }
                continue;
            }
            if (mulExp.getOp(i) == 1) {
                res = new CalculateIns("", temp.getType(), BB, Operation.MUL, res, temp);
            } else if (mulExp.getOp(i) == 2) {
                res = new CalculateIns("", temp.getType(), BB, Operation.SREM, res, temp);
            } else {
                res = new CalculateIns("", temp.getType(), BB, Operation.SDIV, res, temp);
            }
            BB.addIns((Instruction) res);
        }
        return res;
    }

    public static Value calUnaryExp(UnaryExp unaryExp, BasicBlock BB) {
        Value res = null;
        if (unaryExp.getPrimaryExp() != null) {
            res = calPrimExp(unaryExp.getPrimaryExp(), BB);
        } else if (unaryExp.getUnaryExp() != null) {
            res = calUnaryExp(unaryExp.getUnaryExp(), BB);
            if (unaryExp.getOp() == 2) {
                if (res instanceof ConstInt) {
                    res = ((ConstInt) res).neg();
                } else {
                    int bit = ((IntegerType) res.getType()).getBit();
                    res = new CalculateIns("", res.getType(), BB, Operation.SUB,
                        new ConstInt("0", new IntegerType(bit), 0), res);
                    BB.addIns((Instruction) res);
                }
            } else if (unaryExp.getOp() == 3) {
                int bit = ((IntegerType) res.getType()).getBit();
                if (res instanceof ConstInt) {
                    if (((ConstInt) res).getValue() == 0) {
                        return new ConstInt("1", new IntegerType(32), 1);
                    } else {
                        return ConstInt.getZero();
                    }
                }
                res = new CmpIns("", new IntegerType(1), BB, Operation.ICMP, "eq",
                    res, new ConstInt("0", new IntegerType(bit), 0));
                BB.addIns((Instruction) res);
                res = new UnaryIns("", new IntegerType(32), BB, Operation.ZEXT, res);
                BB.addIns((Instruction) res);
            }
        } else {
            IRFunction func = (IRFunction) module.getSymbolTable().search(unaryExp.getFunc());
            ArrayList<Value> params = new ArrayList<>();
            FunctionType funcType = (FunctionType) func.getType();
            for (int i = 0; i < funcType.getParamNum(); i++) {
                params.add(calAddExp(unaryExp.getFuncRParams().getAddExp(i), BB));
            }
            res = new CallIns("", funcType.getRetType(), BB, Operation.CALL, func, params);
            BB.addIns((Instruction) res);
        }
        return res;
    }

    public static Value calPrimExp(PrimaryExp primaryExp, BasicBlock BB) {
        if (primaryExp.getAddExp() != null) {
            return calAddExp(primaryExp.getAddExp(), BB);
        } else if (primaryExp.getlVal() != null) {
            return calLVal(primaryExp.getlVal(), BB);
        } else {
            int value = primaryExp.getNumber();
            return new ConstInt(Integer.toString(value), new IntegerType(32), value);
        }
    }

    public static Value calLVal(LVal lVal, BasicBlock BB) {
        Value value = module.getSymbolTable().search(lVal.getIdent().getName());
        if (value instanceof GlobalVariable globalVariable) {
            Value temp = globalVariable.getValue();
            if (!(temp instanceof ConstInt)) {
                temp = new LoadIns("", new IntegerType(32), BB,
                    Operation.LOAD, globalVariable);
                BB.addIns((Instruction) temp);
            }
            return temp;
        } else if (value instanceof GlobalArray globalArray) {
            ArrayList<Value> index = new ArrayList<>();
            index.add(ConstInt.getZero());
            ArrayList<Integer> numIndex = new ArrayList<>();
            for (int i = 0; i < lVal.getExpNum(); i++) {
                index.add(calAddExp(lVal.getAddExp(i), BB));
                if (index.get(i + 1) instanceof ConstInt constInt) {
                    numIndex.add(constInt.getValue());
                }
            }
            ArrayType arrayType = (ArrayType) ((PointerType) globalArray.getType()).getPointType();
            if (index.size() == arrayType.getDim() + 1) {
                if (index.size() == numIndex.size() + 1) {
                    Value temp = globalArray.getValue(arrayType.getOffset(numIndex));
                    if (temp instanceof ConstInt) {
                        return temp;
                    }
                }
                Instruction temp = new GetIns("", new PointerType(new IntegerType(32)), BB,
                    Operation.GETELEMENTPTR, globalArray, index);
                BB.addIns(temp);
                temp = new LoadIns("", new IntegerType(32), BB, Operation.LOAD, temp);
                BB.addIns(temp);
                return temp;
            } else {
                if (BB.getFather().getName().equals("main")) {
                    globalArray.unCertain();
                }
                ArrayList<Value> zero = new ArrayList<>();
                zero.add(ConstInt.getZero());
                zero.add(ConstInt.getZero());
                Instruction temp = null;
                if (index.size() == 1) {
                    temp = new GetIns("", new PointerType(arrayType.getSonType()), BB,
                        Operation.GETELEMENTPTR, globalArray, zero);
                    BB.addIns(temp);
                    return temp;
                }
                Value v = globalArray;
                Type type = arrayType;
                for (int i = 1; i < index.size(); i++) {
                    type = ((ArrayType) type).getSonType();
                    ArrayList<Value> t = new ArrayList<>();
                    t.add(ConstInt.getZero());
                    t.add(index.get(i));
                    temp = new GetIns("", new PointerType(type), BB,
                        Operation.GETELEMENTPTR, v, t);
                    v = temp;
                    BB.addIns(temp);
                }
                type = ((ArrayType) type).getSonType();
                temp = new GetIns("", new PointerType(type), BB,
                    Operation.GETELEMENTPTR, v, zero);
                BB.addIns(temp);
                return temp;
            }
        } else if (value instanceof AllocaIns allocaIns) {
            if (allocaIns.getDefType() instanceof IntegerType) {
                Value temp = allocaIns.getValue(0);
                if (!(temp instanceof ConstInt)) {
                    temp = new LoadIns("", new IntegerType(32), BB,
                        Operation.LOAD, allocaIns);
                    BB.addIns((Instruction) temp);
                }
                return temp;
            } else if (allocaIns.getDefType() instanceof ArrayType) {
                ArrayList<Value> index = new ArrayList<>();
                index.add(ConstInt.getZero());
                ArrayList<Integer> numIndex = new ArrayList<>();
                for (int i = 0; i < lVal.getExpNum(); i++) {
                    index.add(calAddExp(lVal.getAddExp(i), BB));
                    if (index.get(i + 1) instanceof ConstInt constInt) {
                        numIndex.add(constInt.getValue());
                    }
                }
                ArrayType arrayType =
                    (ArrayType) ((PointerType) allocaIns.getType()).getPointType();
                if (index.size() == arrayType.getDim() + 1) {
                    if (index.size() == numIndex.size() + 1) {
                        Value temp = allocaIns.getValue(arrayType.getOffset(numIndex));
                        if (temp instanceof ConstInt) {
                            return temp;
                        }
                    }
                    Instruction temp = new GetIns("", new PointerType(new IntegerType(32)), BB,
                        Operation.GETELEMENTPTR, allocaIns, index);
                    BB.addIns(temp);
                    temp = new LoadIns("", new IntegerType(32), BB, Operation.LOAD, temp);
                    BB.addIns(temp);
                    return temp;
                } else {
                    ArrayList<Value> zero = new ArrayList<>();
                    zero.add(ConstInt.getZero());
                    zero.add(ConstInt.getZero());
                    Instruction temp = null;
                    if (index.size() == 1) {
                        temp = new GetIns("", new PointerType(arrayType.getSonType()), BB,
                            Operation.GETELEMENTPTR, allocaIns, zero);
                        BB.addIns(temp);
                        return temp;
                    }
                    temp = allocaIns;
                    Type type = arrayType;
                    for (int i = 1; i < index.size(); i++) {
                        type = ((ArrayType) type).getSonType();
                        ArrayList<Value> t = new ArrayList<>();
                        t.add(ConstInt.getZero());
                        t.add(index.get(i));
                        temp = new GetIns("", new PointerType(type), BB,
                            Operation.GETELEMENTPTR, temp, t);
                        BB.addIns(temp);
                    }
                    type = ((ArrayType) type).getSonType();
                    temp = new GetIns("", new PointerType(type), BB,
                        Operation.GETELEMENTPTR, temp, zero);
                    BB.addIns(temp);
                    return temp;
                }
            } else { //instanceof PointerType
                ArrayList<Value> index = new ArrayList<>();
                for (int i = 0; i < lVal.getExpNum(); i++) {
                    index.add(calAddExp(lVal.getAddExp(i), BB));
                }
                Instruction temp =
                    new LoadIns("", allocaIns.getDefType(), BB, Operation.LOAD, allocaIns);
                BB.addIns(temp);
                if (index.size() == 0) {
                    return temp;
                }
                ArrayList<Value> t = new ArrayList<>();
                t.add(index.get(0));
                temp = new GetIns("", temp.getType(),
                    BB, Operation.GETELEMENTPTR, temp, t);
                BB.addIns(temp);
                if (((PointerType) temp.getType()).getPointType() instanceof IntegerType) {
                    temp = new LoadIns("", new IntegerType(32), BB, Operation.LOAD, temp);
                    BB.addIns(temp);
                    return temp;
                }
                index.set(0, ConstInt.getZero());
                ArrayType arrayType =
                    (ArrayType) ((PointerType) temp.getType()).getPointType();
                if (index.size() == arrayType.getDim() + 1) {
                    temp = new GetIns("", new PointerType(new IntegerType(32)), BB,
                        Operation.GETELEMENTPTR, temp, index);
                    BB.addIns(temp);
                    temp = new LoadIns("", new IntegerType(32), BB, Operation.LOAD, temp);
                    BB.addIns(temp);
                    return temp;
                } else {
                    ArrayList<Value> zero = new ArrayList<>();
                    zero.add(ConstInt.getZero());
                    zero.add(ConstInt.getZero());
                    if (index.size() == 1) {
                        temp = new GetIns("", new PointerType(arrayType.getSonType()), BB,
                            Operation.GETELEMENTPTR, temp, zero);
                        BB.addIns(temp);
                        return temp;
                    }
                    Type type = arrayType;
                    for (int i = 1; i < index.size(); i++) {
                        type = ((ArrayType) type).getSonType();
                        t.clear();
                        t.add(ConstInt.getZero());
                        t.add(index.get(i));
                        temp = new GetIns("", new PointerType(type), BB,
                            Operation.GETELEMENTPTR, temp, t);
                        BB.addIns(temp);
                    }
                    type = ((ArrayType) type).getSonType();
                    temp = new GetIns("", new PointerType(type), BB,
                        Operation.GETELEMENTPTR, temp, zero);
                    BB.addIns(temp);
                    return temp;
                }
            }
        }
        return null;
    }
}
