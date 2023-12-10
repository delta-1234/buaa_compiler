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

    public void destroy() {
        for (int i = 0; i < getOperands().size(); i++) {
            getOperands().get(i).getValue().getUseList().remove(getOperands().get(i));
        }
        for (int i = 0; i < getUseList().size(); i++) {
            getUseList().get(i).getUser().getOperands().remove(getUseList().get(i));
        }
    }
}
