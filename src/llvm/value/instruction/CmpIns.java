package llvm.value.instruction;

import llvm.Use;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.Value;

public class CmpIns extends Instruction{
    private int cond;
    private Value op1;
    private Value op2;
    private static int count = 0;
    private static String [] condName = {"eq", "ne", "ugt", "uge",
    "ult", "ule", "sgt", "sge", "slt", "sle"};
    //e=equal,n=not,u=unsigned,g=greater,l=less,s=signed,t=than

    //<result> = icmp <cond> <ty> <op1>, <op2>
    public CmpIns(String name, Type type, BasicBlock basicBlock, Operation op,
                  String cond, Value op1, Value op2) {
        super(name, type, basicBlock, op);
        for (int i = 0; i < condName.length; i++) {
            if (condName[i].equals(cond)) {
                this.cond = i;
                break;
            }
        }
        this.op1 = op1;
        this.op2 = op2;
        setIdent("%cmp" + count);
        count++;
        Use.getInstance(op1, this);
        Use.getInstance(op2, this);
    }

    public String getCond() {
        return condName[cond];
    }

    public Value getOp1() {
        return op1;
    }

    public Value getOp2() {
        return op2;
    }

    ////<result> = icmp <cond> <ty> <op1>, <op2>
    @Override
    public String toString() {
        return getIdent() + " = icmp " + condName[cond] + " " +
            op1.getType() + " " + op1.getIdent() + ", " + op2.getIdent() + "\n";
    }

    public void setOp1(Value op1) {
        this.op1 = op1;
        Use.getInstance(op1, this);
    }

    public void setOp2(Value op2) {
        this.op2 = op2;
        Use.getInstance(op2, this);
    }

    public int getCondNum() {
        return cond;
    }
}
