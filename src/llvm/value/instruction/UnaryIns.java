package llvm.value.instruction;

import llvm.Use;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.Value;

import java.util.HashMap;

public class UnaryIns extends Instruction{
    private Value value;
    private static int count = 0;

    //<result> = zext <ty> <value> to <ty2>
    //<result> = trunc <ty> <value> to <ty2>
    public UnaryIns(String name, Type type, BasicBlock basicBlock, Operation op,
                    Value value) {
        super(name, type, basicBlock, op);
        this.value = value;
        setIdent("%zot" + count);
        count++;
        Use.getInstance(value, this);
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (getOp() == Operation.ZEXT) {
            return getIdent() + " = zext " + value.getType() + " " + value.getIdent() +
                " to " + getType() + "\n";
        }
        return getIdent() + " = trunc " + value.getType() + " " + value.getIdent() +
            " to " + getType() + "\n";
    }

    public void setValue(Value value) {
        this.value = value;
        Use.getInstance(value, this);
    }

    @Override
    public Instruction clone(HashMap<BasicBlock, BasicBlock> oldBBToNew,
                             HashMap<Value, Value> oldValueToNew) {
        BasicBlock father = oldBBToNew.get(getParent());
        Value v = oldValueToNew.getOrDefault(value, value);
        UnaryIns unaryIns = new UnaryIns("", getType(), father, getOp(), v);
        oldValueToNew.put(this, unaryIns);
        return unaryIns;
    }
}
