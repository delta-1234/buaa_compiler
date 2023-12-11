package llvm.value.instruction;

import llvm.Use;
import llvm.type.PointerType;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.Value;
import llvm.value.constant.ConstInt;

import java.util.ArrayList;

public class GetIns extends Instruction{
    private Value value;
    private ArrayList<Value> index;
    private static int count = 0;
    //<result> = getelementptr <ty>, <ty>* <ptrval>, {<ty> <index>}*
    //<result> = getelementptr inbounds <ty>, <ty>* <ptrval>{, [inrange] <ty> <idx>}*
    public GetIns(String name, Type type, BasicBlock basicBlock, Operation op,
                  Value value, ArrayList<Value> index) {
        super(name, type, basicBlock, op);
        this.value = value;
        this.index = index;
        setIdent("%get" + count);
        count++;
        Use.getInstance(value, this);
        for (int i = 0; i < index.size(); i++) {
            Use.getInstance(index.get(i), this);
        }
    }

    public Value getValue() {
        return value;
    }

    public ArrayList<Value> getIndex() {
        return index;
    }

    //<result> = getelementptr <ty>, <ty>* <ptrval>, {<ty> <index>}*
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getIdent());
        sb.append(" = getelementptr ");
        sb.append(((PointerType)value.getType()).getPointType());
        sb.append(", ");
        sb.append(value.getType());
        sb.append(" ");
        sb.append(value.getIdent());
        for (int i = 0; i < index.size(); i++) {
            if (index.get(i) instanceof ConstInt) {
                sb.append(", ");
                sb.append(index.get(i));
            } else {
                sb.append(", i32 ");
                sb.append(index.get(i).getIdent());
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    public void setValue(Value value) {
        this.value = value;
        Use.getInstance(value, this);
    }
}
