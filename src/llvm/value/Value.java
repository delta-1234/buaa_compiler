package llvm.value;

import llvm.Use;
import llvm.type.Type;
import llvm.value.instruction.BrIns;
import llvm.value.instruction.CalculateIns;
import llvm.value.instruction.CallIns;
import llvm.value.instruction.CmpIns;
import llvm.value.instruction.GetIns;
import llvm.value.instruction.Instruction;
import llvm.value.instruction.Phi;
import llvm.value.instruction.RetIns;
import llvm.value.instruction.StoreIns;
import llvm.value.instruction.UnaryIns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Value {
    private String ident;
    private String name;
    private Type type;
    private ArrayList<Use> useList;

    public Value(String name, Type type) {
        this.name = name;
        this.type = type;
        this.useList = new ArrayList<>();
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getName() {
        if (name.equals("") && ident.charAt(0) == '%') {
            name = ident.split("%")[1];
        }
        return name;
    }

    public Type getType() {
        return type;
    }

    public ArrayList<Use> getUseList() {
        return useList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Value value = (Value) o;
        return Objects.equals(ident, value.ident);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ident);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNextUse(BasicBlock BB, int nowLoc) {
        int min = -1;
        for (Use use : useList) {
            if (use.getUser() instanceof Instruction ins &&
                ins.getParent().equals(BB)) {
                if (ins.getLocation() < nowLoc) {
                    continue;
                }
                if (min < 0) {
                    min = ins.getLocation() - nowLoc;
                    continue;
                }
                if (min > ins.getLocation() - nowLoc) {
                    min = ins.getLocation() - nowLoc;
                }
            }
        }
        if (min >= 0) {
            return min;
        }
        return -1;
    }

    public HashSet<BasicBlock> activeBlock() {
        HashSet<BasicBlock> blocks = new HashSet<>();
        for (int i = 0; i < useList.size(); i++) {
            User user = useList.get(i).getUser();
            if (user instanceof Instruction instruction) {
                blocks.add(instruction.getParent());
            }
        }
        if (this instanceof Instruction instruction) {
            blocks.add(instruction.getParent());
        }
        return blocks;
    }

    public void replaceUsed(Value value) {
        ArrayList<Use> temp = new ArrayList<>(useList);
        for (Use u : temp) {
            if (!(u.getUser() instanceof Instruction useIns)) {
                continue;
            }
            if (useIns instanceof BrIns brIns) {
                brIns.setValue(value);
            } else if (useIns instanceof CalculateIns calculateIns) {
                if (calculateIns.getOp1().equals(this)) {
                    calculateIns.setOp1(value);
                }
                if (calculateIns.getOp2().equals(this)) {
                    calculateIns.setOp2(value);
                }
            } else if (useIns instanceof CallIns callIns) {
                for (int i = 0; i < callIns.getRealParams().size(); i++) {
                    if (callIns.getRealParams().get(i).equals(this)) {
                        callIns.getRealParams().set(i, value);
                        Use.getInstance(value, callIns);
                    }
                }
            } else if (useIns instanceof CmpIns cmpIns) {
                if (cmpIns.getOp1().equals(this)) {
                    cmpIns.setOp1(value);
                }
                if (cmpIns.getOp2().equals(this)) {
                    cmpIns.setOp2(value);
                }
            } else if (useIns instanceof GetIns getIns) {
                for (int i = 0; i < getIns.getIndex().size(); i++) {
                    if (getIns.getIndex().get(i).equals(this)) {
                        getIns.getIndex().set(i, value);
                        Use.getInstance(value, getIns);
                    }
                }
            } else if (useIns instanceof Phi phi) {
                phi.replace(value, this);
            } else if (useIns instanceof RetIns retIns) {
                retIns.setValue(value);
            } else if (useIns instanceof StoreIns storeIns) {
                storeIns.setValue(value);
            } else if (useIns instanceof UnaryIns unaryIns) {
                unaryIns.setValue(value);
            }
        }
    }
}
