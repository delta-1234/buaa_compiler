package llvm.type;

import llvm.value.Argument;

import java.util.ArrayList;

public class FunctionType implements Type {
    private int paramNum;
    private ArrayList<Argument> params;
    private int retType; //0为void

    public FunctionType(int retType, ArrayList<Argument> params) {
        this.retType = retType;
        this.paramNum = params.size();
        this.params = params;
    }

    public Type getRetType() {
        if (retType == 0) {
            return new VoidType();
        } else {
            return new IntegerType(32);
        }
    }

    public int getParamNum() {
        return paramNum;
    }

    public Argument getParam(int i) {
        return params.get(i);
    }

    @Override
    public boolean isInteger() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public boolean isPointer() {
        return false;
    }

    @Override
    public String toString() {
        return "fc";
    }
}
