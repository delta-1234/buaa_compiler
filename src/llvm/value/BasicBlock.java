package llvm.value;

import llvm.type.Type;
import llvm.value.constant.IRFunction;
import llvm.value.instruction.AllocaIns;
import llvm.value.instruction.BrIns;
import llvm.value.instruction.Instruction;
import llvm.value.instruction.LoadIns;
import llvm.value.instruction.RetIns;
import llvm.value.instruction.StoreIns;

import java.util.ArrayList;
import java.util.HashSet;

public class BasicBlock extends Value {
    private ArrayList<Instruction> instructions;
    private IRFunction father;
    private static int count = 0;
    private HashSet<Value> defValue;
    private HashSet<Value> useValue;
    private ArrayList<BasicBlock> succeed;
    private HashSet<Value> activateVar;

    public BasicBlock(String name, Type type, IRFunction father) {
        super(name, type);
        instructions = new ArrayList<>();
        this.father = father;
        setIdent("%B" + count);
        setName("B" + count);
        count++;
        defValue = new HashSet<>();
        useValue = new HashSet<>();
        succeed = new ArrayList<>();
        activateVar = new HashSet<>();
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

    public void calUseDef() {
        int begin = 0;
        if (father.getBasicBlocks().get(0).equals(this)) {
            begin = Math.min(father.getParamNum(), 4) * 2;
        }
        for (int i = begin; i < instructions.size(); i++) {
            if (instructions.get(i) instanceof LoadIns loadIns &&
                !defValue.contains(loadIns.getPointer()) &&
                loadIns.getPointer() instanceof AllocaIns allocaIns) {
                if (allocaIns.isArg()) {
                    continue;
                }
                useValue.add(loadIns.getPointer());
            } else if (instructions.get(i) instanceof StoreIns storeIns &&
                !useValue.contains(storeIns.getPointer()) &&
                storeIns.getPointer() instanceof AllocaIns allocaIns) {
                if (allocaIns.isArg()) {
                    continue;
                }
                defValue.add(storeIns.getPointer());
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

    public ArrayList<BasicBlock> getSucceed() {
        return succeed;
    }

    public HashSet<Value> getDefValue() {
        return defValue;
    }

    public HashSet<Value> getUseValue() {
        return useValue;
    }

    public HashSet<Value> getActivateVar() {
        return activateVar;
    }
}
