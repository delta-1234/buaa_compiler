package llvm.value.instruction;

import llvm.Use;
import llvm.type.IntegerType;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.Value;

import java.util.HashMap;

public class CalculateIns extends Instruction {
    private Value op1;
    private Value op2;
    private static int count = 0;

    public CalculateIns(String name, Type type, BasicBlock basicBlock, Operation op,
                        Value op1, Value op2) {
        super(name, type, basicBlock, op);
        this.op1 = op1;
        this.op2 = op2;
        setIdent("%calc" + count);
        count++;
        Use.getInstance(op1, this);
        Use.getInstance(op2, this);
    }

    public Value getOp1() {
        return op1;
    }

    public Value getOp2() {
        return op2;
    }

    @Override
    public String toString() {
        return getIdent() + " = " + getOp().toString().toLowerCase() +
            " " + getType() + " " + op1.getIdent() + ", " + op2.getIdent() + "\n";
    }

    public void setOp1(Value op1) {
        this.op1 = op1;
        Use.getInstance(op1, this);
    }

    public void setOp2(Value op2) {
        this.op2 = op2;
        Use.getInstance(op2, this);
    }

    public int getOpNum() {
        if (getOp() == Operation.ADD) {
            return 1;
        } else if (getOp() == Operation.SUB) {
            return 2;
        } else if (getOp() == Operation.MUL) {
            return 3;
        } else if (getOp() == Operation.SDIV) {
            return 4;
        } else {
            return 5;
        }
    }

    @Override
    public Instruction clone(HashMap<BasicBlock, BasicBlock> oldBBToNew,
                             HashMap<Value, Value> oldValueToNew) {
        BasicBlock father = oldBBToNew.get(getParent());
        Value v1 = oldValueToNew.getOrDefault(op1, op1);
        Value v2 = oldValueToNew.getOrDefault(op2, op2);
        CalculateIns calIns = new CalculateIns("", getType(), father, getOp(), v1, v2);
        oldValueToNew.put(this, calIns);
        return calIns;
    }
}
