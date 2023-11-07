package llvm.value.constant;

import llvm.type.Type;

public class GlobalValue extends Constant {
    public GlobalValue(String name, Type type) {
        super(name, type);
    }
}
