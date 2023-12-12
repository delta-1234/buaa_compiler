package llvm.value.instruction;

import llvm.Use;
import llvm.type.FunctionType;
import llvm.type.Type;
import llvm.type.VoidType;
import llvm.value.BasicBlock;
import llvm.value.Value;
import llvm.value.constant.ConstInt;
import llvm.value.constant.IRFunction;

import java.util.ArrayList;
import java.util.HashMap;

public class CallIns extends Instruction {
    private IRFunction func;
    private ArrayList<Value> realParams;
    private static int count = 0;

    //<result> = call [ret attrs] <ty> <fnptrval>(<function args>)
    //call void <fnptrval>(<function args>)
    public CallIns(String name, Type type, BasicBlock basicBlock, Operation op,
                   IRFunction func, ArrayList<Value> realParams) {
        super(name, type, basicBlock, op);
        this.func = func;
        this.realParams = realParams;
        if (((FunctionType) func.getType()).getRetType() instanceof VoidType) {
            setIdent("call" + count);
        } else {
            setIdent("%call" + count);
        }
        count++;
        Use.getInstance(func, this);
        for (int i = 0; i < realParams.size(); i++) {
            Use.getInstance(realParams.get(i), this);
        }
    }

    public IRFunction getFunc() {
        return func;
    }

    public ArrayList<Value> getRealParams() {
        return realParams;
    }

    //<result> = call [ret attrs] <ty> <fnptrval>(<function args>)
    //call void <fnptrval>(<function args>)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (((FunctionType) func.getType()).getRetType() instanceof VoidType) {
            sb.append("call void " + func.getIdent() + "(");
        } else {
            sb.append(getIdent() + " = call " + ((FunctionType) func.getType()).getRetType() +
                " " + func.getIdent() + "(");
        }
        for (int i = 0; i < realParams.size(); i++) {
            if (realParams.get(i) instanceof ConstInt) {
                sb.append(realParams.get(i));
            } else {
                sb.append(realParams.get(i).getType());
                sb.append(" ");
                sb.append(realParams.get(i).getIdent());
            }
            if (i != realParams.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")\n");
        return sb.toString();
    }

    @Override
    public Instruction clone(HashMap<BasicBlock, BasicBlock> oldBBToNew,
                             HashMap<Value, Value> oldValueToNew) {
        BasicBlock father = oldBBToNew.get(getParent());
        ArrayList<Value> params = new ArrayList<>();
        for (int i = 0; i < realParams.size(); i++) {
            params.add(oldValueToNew.getOrDefault(realParams.get(i), realParams.get(i)));
        }
        CallIns callIns = new CallIns("", getType(), father, getOp(), func, params);
        oldValueToNew.put(this, callIns);
        return callIns;
    }
}
