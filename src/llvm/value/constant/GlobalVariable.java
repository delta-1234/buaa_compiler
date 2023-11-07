package llvm.value.constant;

import llvm.Use;
import llvm.type.Type;
import llvm.value.Value;

public class GlobalVariable extends GlobalValue {
    private Value result;
    private boolean isConst;
    private boolean unCertain;

    public GlobalVariable(String name, Type type, ConstInt value, boolean isConst) {
        super(name, type);
        result = value;
        this.isConst = isConst;
        Use.getInstance(value, this);
        setIdent("@" + name);
    }

    public Value getValue() {
        if (unCertain) {
            return null;
        }
        return result;
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

    public void certain() {
        unCertain = false;
    }

    @Override
    public String toString() {
        if (isConst) {
            return this.getIdent() + " = dso_local constant " + result + "\n";
        }
        return this.getIdent() + " = dso_local global " + result + "\n";
    }
}
