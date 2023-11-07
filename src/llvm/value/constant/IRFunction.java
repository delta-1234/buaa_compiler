package llvm.value.constant;

import llvm.type.FunctionType;
import llvm.type.Type;
import llvm.type.VoidType;
import llvm.value.BasicBlock;
import llvm.value.instruction.BrIns;
import llvm.value.instruction.Instruction;
import llvm.value.instruction.Operation;
import llvm.value.instruction.RetIns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class IRFunction extends GlobalValue {
    private ArrayList<BasicBlock> basicBlocks;
    private FunctionType functionType;

    public IRFunction(String name, Type type) {
        super(name, type);
        setIdent("@" + name);
        functionType = (FunctionType) type;
        basicBlocks = new ArrayList<>();
    }

    public BasicBlock getBB(int i) { //超出index返回null
        if (i >= basicBlocks.size()) {
            return null;
        }
        return basicBlocks.get(i);
    }

    public BasicBlock getLastBB() {
        return basicBlocks.get(basicBlocks.size() - 1);
    }

    public int getBBNum() {
        return basicBlocks.size();
    }

    public void addBB(BasicBlock basicBlock) {
        basicBlocks.add(basicBlock);
    }

    public void deleteDead() {
        if (getLastBB().getLastIns() == null || !(getLastBB().getLastIns() instanceof RetIns)) {
            getLastBB().addIns(
                new RetIns("", new VoidType(), getLastBB(), Operation.RET));
        }
        for (int i = 0; i < basicBlocks.size(); i++) {
            basicBlocks.get(i).deleteDead();
        }
        if (getLastBB().getLastIns() == null || !(getLastBB().getLastIns() instanceof RetIns)) {
            getLastBB().addIns(
                new RetIns("", new VoidType(), getLastBB(), Operation.RET));
        }
        if (basicBlocks.size() == 1) {
            return;
        }
        ArrayList<Integer> rec = new ArrayList<>();
        for (int k = 0; k < basicBlocks.size(); k++) {
            HashSet<BasicBlock> hasFather = new HashSet<>();
            for (int i = 0; i < basicBlocks.size(); i++) {
                Instruction ins = basicBlocks.get(i).getLastIns();
                if (!(ins instanceof BrIns brIns)) {
                    continue;
                }
                if (brIns.getDest() != null) {
                    hasFather.add(brIns.getDest());
                    BasicBlock dest = brIns.getDest();
                    if (dest.getInstructions().size() != 1) {
                        continue;
                    }
                    if (!(dest.getLastIns() instanceof BrIns)) {
                        continue;
                    }
                    BrIns temp = (BrIns) dest.getLastIns();
                    if (temp.getDest() != null) {
                        brIns.setDest(temp.getDest());
                    } else {
                        brIns.setDest(null);
                        brIns.setTrueBB(temp.getTrueBB());
                        brIns.setFalseBB(temp.getFalseBB());
                        brIns.setCond(temp.getCond());
                    }
                } else {
                    hasFather.add(brIns.getTrueBB());
                    hasFather.add(brIns.getFalseBB());
                    BasicBlock trueBB = brIns.getTrueBB();
                    BasicBlock falseBB = brIns.getFalseBB();
                    if (trueBB.getInstructions().size() != 1) {
                        continue;
                    }
                    if (!(trueBB.getLastIns() instanceof BrIns)) {
                        continue;
                    }
                    BasicBlock temp = ((BrIns) trueBB.getLastIns()).getDest();
                    if (temp != null) {
                        brIns.setTrueBB(temp);
                    }
                    if (falseBB.getInstructions().size() != 1) {
                        continue;
                    }
                    if (!(falseBB.getLastIns() instanceof BrIns)) {
                        continue;
                    }
                    temp = ((BrIns) falseBB.getLastIns()).getDest();
                    if (temp != null) {
                        brIns.setFalseBB(temp);
                    }
                }
            }
            for (int i = 1; i < basicBlocks.size(); i++) {
                if (!hasFather.contains(basicBlocks.get(i))) {
                    ArrayList<Instruction> ins = basicBlocks.get(i).getInstructions();
                    for (int j = 0; j < ins.size(); j++) {
                        ins.get(j).destroy();
                    }
                    basicBlocks.remove(i);
                    i--;
                }
            }
            rec.add(basicBlocks.size());
            if (k > 3 && Objects.equals(rec.get(k - 1), rec.get(k - 2)) &&
                Objects.equals(rec.get(k - 2), rec.get(k - 3))) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("define dso_local ");
        sb.append(functionType.getRetType());
        sb.append(" ");
        sb.append(getIdent());
        sb.append("(");
        for (int i = 0; i < functionType.getParamNum(); i++) {
            sb.append(functionType.getParam(i));
            if (i != functionType.getParamNum() - 1) {
                sb.append(", ");
            }
        }
        sb.append("){\n");
        for (int i = 0; i < basicBlocks.get(0).getInstructions().size(); i++) {
            sb.append("    ");
            sb.append(basicBlocks.get(0).getInstructions().get(i));
        }
        for (int i = 1; i < basicBlocks.size(); i++) {
            sb.append(basicBlocks.get(i));
        }
        sb.append("}\n");
        return sb.toString();
    }
}
