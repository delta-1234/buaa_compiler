package llvm.value.constant;

import llvm.Use;
import llvm.type.ArrayType;
import llvm.type.PointerType;
import llvm.type.Type;
import llvm.value.Value;

import java.util.HashMap;

public class GlobalArray extends GlobalValue {
    private HashMap<Integer, Value> result;
    private boolean isConst;
    private boolean unCertain;

    public GlobalArray(String name, Type type, boolean isConst) {
        super(name, type);
        result = new HashMap<>();
        this.isConst = isConst;
        unCertain = false;
        setIdent("@" + name);
    }

    public Value getValue(int i) {
        if (unCertain) {
            return null;
        }
        if (!result.containsKey(i)) {
            return ConstInt.getZero();
        }
        return result.get(i);
    }

    public void putValue(int loc, Value value) {
        result.put(loc, value);
    }

    public boolean isConst() {
        return isConst;
    }

    public void unCertain() {
        if (isConst) {
            return;
        }
        unCertain = true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isConst) {
            sb.append(getIdent() + " = dso_local constant ");
        } else {
            sb.append(getIdent() + " = dso_local global ");
        }
        ArrayType arrayType = (ArrayType) ((PointerType) getType()).getPointType();
        output(sb, 0, arrayType, 0);
        sb.append("\n");
        return sb.toString();
    }

    private boolean output(StringBuilder sb, int dim, ArrayType arrayType, int loc) {
        for (int i = dim; i < arrayType.getDim(); i++) {
            sb.append("[" + arrayType.getDimSize(i) + " x ");
        }
        sb.append("i32");
        for (int i = dim; i < arrayType.getDim(); i++) {
            sb.append("]");
        }
        sb.append(" ");
        if (dim == arrayType.getDim() - 1) {
            if (!result.containsKey(loc)) {
                sb.append("zeroinitializer");
                return true;
            }
            sb.append("[i32 " + ((ConstInt) result.get(loc)).getValue());
            for (int i = 1; i < arrayType.getDimSize(dim); i++) {
                if (result.containsKey(loc+i)) {
                    sb.append(", i32 " + ((ConstInt) result.get(loc+i)).getValue());
                } else {
                    sb.append(", i32 0");
                }
            }
            sb.append("]");
            return false;
        }
        StringBuilder sb1 = new StringBuilder();
        boolean flag = output(sb1, dim+1, arrayType, loc);
        for (int i = 1; i < arrayType.getDimSize(dim); i++) {
            sb1.append(", ");
            boolean temp = output(sb1, dim+1, arrayType, loc+i*arrayType.getP(dim));
            if (!temp) {
                flag = false;
            }
        }
        if (flag) {
            sb.append("zeroinitializer");
            return true;
        } else {
            sb.append("[");
            sb.append(sb1);
            sb.append("]");
            return false;
        }
    }
}
