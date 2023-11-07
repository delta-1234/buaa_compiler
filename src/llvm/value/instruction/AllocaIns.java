package llvm.value.instruction;

import llvm.Use;
import llvm.type.IntegerType;
import llvm.type.PointerType;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.Value;
import llvm.value.constant.ConstInt;

import java.util.HashMap;

public class AllocaIns extends Instruction {
    private static int count = 0;
    private Type pointType;
    private HashMap<Integer, Value> result;
    private boolean isConst;

    //<result> = alloca <type>
    public AllocaIns(String name, Type type, BasicBlock basicBlock, Operation op, boolean isConst) {
        super(name, type, basicBlock, op);
        pointType = ((PointerType) type).getPointType();
        result = new HashMap<>();
        this.isConst = isConst;
        setIdent("%def" + count);
        count++;
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
}
