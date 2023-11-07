package llvm.value.constant;

import llvm.type.Type;
import llvm.value.User;

public class Constant extends User {
    public Constant(String name, Type type) {
        super(name, type);
    }
}
