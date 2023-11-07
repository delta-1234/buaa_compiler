package llvm.value;

import llvm.Use;
import llvm.type.Type;

import java.util.ArrayList;

public class User extends Value {
    private ArrayList<Use> operands;

    public User(String name, Type type) {
        super(name, type);
        this.operands = new ArrayList<>();
    }

    public ArrayList<Use> getOperands() {
        return operands;
    }
}
