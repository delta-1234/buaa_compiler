package llvm.value.instruction;

import llvm.Use;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.Value;

public class LoadIns extends Instruction {
    private Value pointer;
    private static int count = 0;

    //<result> = load <ty>, <ty>* <pointer>
    public LoadIns(String name, Type type, BasicBlock basicBlock,
                   Operation op, Value pointer) {
        super(name, type, basicBlock, op);
        this.pointer = pointer;
        setIdent("%ld" + count);
        count++;
        Use.getInstance(pointer, this);
    }

    public Value getPointer() {
        return pointer;
    }

    @Override
    public String toString() {
        return getIdent() + " = load " +
            getType() + ", " + pointer.getType() + " " + pointer.getIdent() + "\n";
    }
}
