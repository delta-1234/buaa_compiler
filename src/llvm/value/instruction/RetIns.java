package llvm.value.instruction;

import llvm.Use;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.Value;
import llvm.value.constant.ConstInt;

public class RetIns extends Instruction{
    private Value value;
    private static int count = 0;
    //ret <type> <value> ,ret void
    public RetIns(String name, Type type, BasicBlock basicBlock, Operation op, Value value) {
        super(name, type, basicBlock, op);
        this.value = value;
        setIdent("ret" + count);
        count++;
        Use.getInstance(value, this);
    }

    public RetIns(String name, Type type, BasicBlock basicBlock, Operation op) {
        super(name, type, basicBlock, op);
        this.value = null;
        setIdent("ret" + count);
        count++;
    }

    public Value getValue() {
        return value;
    }

    //ret <type> <value> ,ret void
    @Override
    public String toString() {
        if (value != null) {
            return "ret " + value.getType() + " " + value.getIdent() + "\n";
        }
        return "ret void\n";
    }
}
