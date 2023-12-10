package llvm.value.instruction;

import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.User;

public class Instruction extends User {
    private BasicBlock parent;
    private Operation op;

    public Instruction(String name, Type type, BasicBlock basicBlock, Operation op) {
        super(name, type);
        this.parent = basicBlock;
        this.op = op;
    }

    public BasicBlock getParent() {
        return parent;
    }

    public Operation getOp() {
        return op;
    }

    public int getLocation() {
        return parent.getInstructions().indexOf(this);
    }
}
