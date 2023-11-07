package llvm.value;

import llvm.type.Type;
import llvm.value.constant.IRFunction;
import llvm.value.instruction.BrIns;
import llvm.value.instruction.Instruction;
import llvm.value.instruction.RetIns;

import java.util.ArrayList;

public class BasicBlock extends Value {
    private ArrayList<Instruction> instructions;
    private IRFunction father;
    private static int count = 0;

    public BasicBlock(String name, Type type, IRFunction father) {
        super(name, type);
        instructions = new ArrayList<>();
        this.father = father;
        setIdent("%B" + count);
        setName("B" + count);
        count++;
    }

    public void addIns(Instruction ins) {
        instructions.add(ins);
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public Instruction getLastIns() {
        if (instructions.size() == 0) {
            return null;
        }
        return instructions.get(instructions.size() - 1);
    }

    public void deleteDead() {
        boolean flag = false;
        for (int i = 0; i < instructions.size(); i++) {
            if (flag) {
                instructions.get(i).destroy();
                instructions.remove(i);
                i--;
            }
            if (instructions.get(i) instanceof RetIns ||
                instructions.get(i) instanceof BrIns) {
                flag = true;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n" + getName() + ":\n");
        for (int i = 0; i < instructions.size(); i++) {
            sb.append("    ");
            sb.append(instructions.get(i));
        }
        return sb.toString();
    }

    public IRFunction getFather() {
        return father;
    }
}
