package llvm.value;

import llvm.Use;
import llvm.type.Type;
import llvm.value.constant.IRFunction;

public class Argument extends Value {
    private IRFunction function;
    private static int count = 0;

    public Argument(String name, Type type) {
        super(name, type);
        setIdent("%arg" + count);
        count++;
    }

    public IRFunction getFunction() {
        return function;
    }

    public void setFunction(IRFunction function) {
        this.function = function;
        Use.getInstance(this, function);
    }

    @Override
    public String toString() {
        return getType() + " " + getIdent();
    }
}
