package llvm.value.instruction;

import llvm.type.PointerType;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.Value;

import java.util.HashMap;

public class AllocaIns extends Instruction {
    private static int count = 0;
    private Type pointType;
    private HashMap<Integer, Value> result;
    private boolean isConst;
    private boolean isArg;

    //<result> = alloca <type>
    public AllocaIns(String name, Type type, BasicBlock basicBlock, Operation op, boolean isConst) {
        super(name, type, basicBlock, op);
        pointType = ((PointerType) type).getPointType();
        result = new HashMap<>();
        this.isConst = isConst;
        setIdent("%def" + count);
        count++;
        if (pointType instanceof PointerType) {
            isArg = true;
        } else {
            isArg = false;
        }
    }

    public Type getDefType() {
        return pointType;
    }

    public Value getValue(int i) {
        if (!result.containsKey(i) || !isConst) {
            return null;
        }
        return result.get(i);
    }

    public void putValue(int loc, Value value) {
        result.put(loc, value);
    }

    public boolean isConst() {
        return isConst;
    }

    //<result> = alloca <type>
    @Override
    public String toString() {
        return getIdent() + " = alloca " + pointType + "\n";
    }

    public boolean isArg() {
        return isArg;
    }

    @Override
    public Instruction clone(HashMap<BasicBlock, BasicBlock> oldBBToNew, HashMap<Value, Value> oldValueToNew) {
        BasicBlock father = oldBBToNew.get(getParent());
        AllocaIns allocaIns = new AllocaIns("", getType(), father, getOp(), isConst);
        oldValueToNew.put(this, allocaIns);
        return allocaIns;
    }
}
