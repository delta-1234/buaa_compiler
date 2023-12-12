package llvm.value.instruction;

import llvm.Use;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.Value;

import java.util.HashMap;

public class BrIns extends Instruction {
    private Value value;
    private BasicBlock trueBB;
    private BasicBlock falseBB;
    private BasicBlock dest;
    private static int count = 0;

    //br i1 <cond>, label <iftrue>, label <iffalse>
    //br label <dest>
    public BrIns(String name, Type type, BasicBlock basicBlock, Operation op,
                 Value value, BasicBlock trueBB, BasicBlock falseBB) {
        super(name, type, basicBlock, op);
        this.value = value;
        this.trueBB = trueBB;
        this.falseBB = falseBB;
        dest = null;
        setIdent("br" + count);
        count++;
        Use.getInstance(value, this);
        Use.getInstance(trueBB, this);
        Use.getInstance(falseBB, this);
    }

    public BrIns(String name, Type type, BasicBlock basicBlock, Operation op, BasicBlock dest) {
        super(name, type, basicBlock, op);
        this.dest = dest;
        trueBB = null;
        falseBB = null;
        setIdent("br" + count);
        count++;
        Use.getInstance(dest, this);
    }

    public Value getCond() {
        return value;
    }

    public void setCond(Value value) {
        this.value = value;
    }

    public BasicBlock getTrueBB() {
        return trueBB;
    }

    public BasicBlock getFalseBB() {
        return falseBB;
    }

    public BasicBlock getDest() {
        return dest;
    }

    public void setTrueBB(BasicBlock trueBB) {
        this.trueBB = trueBB;
    }

    public void setFalseBB(BasicBlock falseBB) {
        this.falseBB = falseBB;
    }

    public void setDest(BasicBlock dest) {
        this.dest = dest;
    }

    //br i1 <cond>, label <iftrue>, label <iffalse>
    //br label <dest>
    @Override
    public String toString() {
        if (dest == null) {
            return "br i1 " + value.getIdent() + ", label " + trueBB.getIdent() + ", label " +
                falseBB.getIdent() + "\n";
        }
        return "br label " + dest.getIdent() + "\n";
    }

    public void setValue(Value value) {
        for (Use u : this.getOperands()) {
            if (u.getValue().equals(this.value)) {
                this.getOperands().remove(u);
                this.value.getUseList().remove(u);
                break;
            }
        }
        this.value = value;
        Use.getInstance(value, this);
    }

    public void removeValue() {
        for (Use u : this.getOperands()) {
            if (u.getValue().equals(value)) {
                this.getOperands().remove(u);
                value.getUseList().remove(u);
                break;
            }
        }
        trueBB = null;
        falseBB = null;
    }

    @Override
    public Instruction clone(HashMap<BasicBlock, BasicBlock> oldBBToNew,
                             HashMap<Value, Value> oldValueToNew) {
        BrIns brIns;
        BasicBlock father = oldBBToNew.get(getParent());
        if (dest != null) {
            brIns = new BrIns("", null, father, getOp(), oldBBToNew.get(dest));
        } else {
            Value newValue = oldValueToNew.getOrDefault(value, value);
            brIns = new BrIns("", null, father, getOp(), newValue, oldBBToNew.get(trueBB),
                oldBBToNew.get(falseBB));
        }
        return brIns;
    }
}
