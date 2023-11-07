package llvm.value.instruction;

import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.User;

public class Instruction extends User {
    public BasicBlock parent;
    public Operation op;
    public Instruction(String name, Type type, BasicBlock basicBlock, Operation op) {
        super(name, type);
        this.parent = basicBlock;
        this.op = op;
    }

    public BasicBlock getParent() {
        return parent;
    }

    public void destroy() {
        for (int i = 0; i < getOperands().size(); i++) {
            getOperands().get(i).getValue().getUseList().remove(getOperands().get(i));
        }
    }

    public Operation getOp() {
        return op;
    }
}
