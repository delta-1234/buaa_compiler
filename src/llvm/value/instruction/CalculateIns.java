package llvm.value.instruction;

import llvm.Use;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.Value;
import llvm.value.constant.ConstInt;

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
}
