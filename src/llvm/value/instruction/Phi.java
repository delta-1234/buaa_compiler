package llvm.value.instruction;

import llvm.Use;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Phi extends Instruction {
    private HashMap<BasicBlock, Value> choice;
    private static int count = 0;
    private Value oldValue;
    private HashMap<BasicBlock, Value> output;
    private HashMap<BasicBlock, HashSet<BasicBlock>> CFG;

    public Phi(String name, Type type, BasicBlock basicBlock, Operation op) {
        super(name, type, basicBlock, op);
        choice = new HashMap<>();
        setIdent("%phi" + count);
        count++;
        oldValue = null;
    }

    public void addChoice(Value value) {
        oldValue = value;
        Use.getInstance(value, this);
    }

    public void setChoice(Value newValue, BasicBlock block) {
        choice.put(block, newValue);
        Use.getInstance(newValue, this);
    }

    public void replace(Value newValue, Value oldValue) {
        for (BasicBlock b : choice.keySet()) {
            if (choice.get(b).equals(oldValue)) {
                choice.put(b, newValue);
                Use.getInstance(newValue, this);
            }
        }
    }

    public Value getOldValue() {
        return oldValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getIdent());
        sb.append(" = ").append("phi ");
        sb.append(getType());
        boolean flag = false;
        for (BasicBlock block : choice.keySet()) {
            if (flag) {
                sb.append(", ");
            }
            sb.append(" [").append(choice.get(block).getIdent());
            sb.append(", ").append(block.getIdent());
            sb.append("]");
            flag = true;
        }
        sb.append("\n");
        return sb.toString();
    }

    public HashMap<BasicBlock, Value> getChoice() {
        return choice;
    }
}
