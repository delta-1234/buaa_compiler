package llvm.value.instruction;

import llvm.Use;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.Value;

import java.util.HashMap;

public class RetIns extends Instruction {
    private Value value;
    private static int count = 0;

    //ret <type> <value> ,ret void
    public RetIns(String name, Type type, BasicBlock basicBlock, Operation op, Value value) {
        super(name, type, basicBlock, op);
        this.value = value;
        setIdent("ret" + count);
        count++;
        Use.getInstance(value, this);
    }

    public RetIns(String name, Type type, BasicBlock basicBlock, Operation op) {
        super(name, type, basicBlock, op);
        this.value = null;
        setIdent("ret" + count);
        count++;
    }

    public Value getValue() {
        return value;
    }

    //ret <type> <value> ,ret void
    @Override
    public String toString() {
        if (value != null) {
            return "ret " + value.getType() + " " + value.getIdent() + "\n";
        }
        return "ret void\n";
    }

    public void setValue(Value value) {
        this.value = value;
        Use.getInstance(value, this);
    }

    @Override
    public Instruction clone(HashMap<BasicBlock, BasicBlock> oldBBToNew,
                             HashMap<Value, Value> oldValueToNew) {
        BasicBlock father = oldBBToNew.get(getParent());
        RetIns retIns;
        if (value == null) {
            retIns = new RetIns("", getType(), father, getOp());
        } else {
            retIns = new RetIns("", getType(), father, getOp(),
                oldValueToNew.getOrDefault(value, value));
        }
        return retIns;
    }
}
