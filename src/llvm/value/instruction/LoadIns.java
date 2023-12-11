package llvm.value.instruction;

import llvm.Use;
import llvm.type.Type;
import llvm.value.BasicBlock;
import llvm.value.User;
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

    public LoadIns(String name, Type type, BasicBlock basicBlock,
                   Operation op, Phi phi, Value pointer) {
        super(name, type, basicBlock, op);
        this.pointer = pointer;
        setIdent("%ld" + count);
        count++;
        Use.getInstance(pointer, this);
        for (Use u : phi.getUseList()) {
            User user = u.getUser();
            user.getOperands().remove(u);
            Instruction useIns;
            useIns = (Instruction) u.getUser();
            if (useIns instanceof BrIns brIns) {
                brIns.setValue(this);
            } else if (useIns instanceof CalculateIns calculateIns) {
                if (calculateIns.getOp1().equals(phi)) {
                    calculateIns.setOp1(this);
                }
                if (calculateIns.getOp2().equals(phi)) {
                    calculateIns.setOp2(this);
                }
            } else if (useIns instanceof CallIns callIns) {
                for (int i = 0; i < callIns.getRealParams().size(); i++) {
                    if (callIns.getRealParams().get(i).equals(phi)) {
                        callIns.getRealParams().set(i, this);
                        Use.getInstance(this, callIns);
                    }
                }
            } else if (useIns instanceof CmpIns cmpIns) {
                if (cmpIns.getOp1().equals(phi)) {
                    cmpIns.setOp1(this);
                }
                if (cmpIns.getOp2().equals(phi)) {
                    cmpIns.setOp2(this);
                }
            } else if (useIns instanceof GetIns getIns) {
                for (int i = 0; i < getIns.getIndex().size(); i++) {
                    if (getIns.getIndex().get(i).equals(phi)) {
                        getIns.getIndex().set(i, this);
                        Use.getInstance(this, getIns);
                    }
                }
            } else if (useIns instanceof Phi p) {
                p.replace(this, phi);
            } else if (useIns instanceof RetIns retIns) {
                retIns.setValue(this);
            } else if (useIns instanceof StoreIns storeIns) {
                storeIns.setValue(this);
            } else if (useIns instanceof UnaryIns unaryIns) {
                unaryIns.setValue(this);
            }
        }
    }

    public Value getPointer() {
        return pointer;
    }

    @Override
    public String toString() {
        return getIdent() + " = load " +
            getType() + ", " + pointer.getType() + " " + pointer.getIdent() + "\n";
    }

    public void setPointer(Value pointer) {
        this.pointer = pointer;
        Use.getInstance(pointer, this);
    }
}
