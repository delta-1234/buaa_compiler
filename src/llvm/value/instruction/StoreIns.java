package llvm.value.instruction;

import llvm.Use;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.User;
import llvm.value.Value;

import java.util.HashMap;

public class StoreIns extends Instruction {
    private Value value;
    private User pointer;
    private static int count = 0;

    //store <ty> <value>, <ty>* <pointer>
    public StoreIns(String name, Type type, BasicBlock basicBlock, Operation op,
                    Value value, User pointer) {
        super(name, type, basicBlock, op);
        this.value = value;
        this.pointer = pointer;
        setIdent("st" + count);
        count++;
        Use.getInstance(value, this);
        Use.getInstance(this, pointer);
    }

    public Value getValue() {
        return value;
    }

    public User getPointer() {
        return pointer;
    }

    //store <ty> <value>, <ty>* <pointer>
    @Override
    public String toString() {
        return "store " + value.getType() + " " + value.getIdent() + ", " +
            pointer.getType() + " " + pointer.getIdent() + "\n";
    }

    public void setValue(Value value) {
        this.value = value;
        Use.getInstance(value, this);
    }

    public void setPointer(User pointer) {
        this.pointer = pointer;
        Use.getInstance(this, pointer);
    }

    @Override
    public Instruction clone(HashMap<BasicBlock, BasicBlock> oldBBToNew,
                             HashMap<Value, Value> oldValueToNew) {
        BasicBlock father = oldBBToNew.get(getParent());
        Value v = oldValueToNew.getOrDefault(value, value);
        Value p = oldValueToNew.getOrDefault(pointer, pointer);
        StoreIns storeIns = new StoreIns("", getType(), father, getOp(), v, (User) p);
        return storeIns;
    }
}
