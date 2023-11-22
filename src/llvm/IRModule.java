package llvm;

import llvm.value.constant.GlobalArray;
import llvm.value.constant.GlobalVariable;
import llvm.value.constant.IRFunction;

import java.util.ArrayList;

public class IRModule {
    private IRSymbolTable irSymbolTable;
    private ArrayList<IRFunction> functions;
    private ArrayList<GlobalVariable> globalVars;
    private ArrayList<GlobalArray> globalArrays;

    public IRModule() {
        irSymbolTable = new IRSymbolTable();
        functions = new ArrayList<>();
        globalVars = new ArrayList<>();
        globalArrays = new ArrayList<>();
    }

    public void addFunction(IRFunction func) {
        functions.add(func);
    }

    public void addGlobalVar(GlobalVariable globalVar) {
        globalVars.add(globalVar);
    }

    public void addGlobalArr(GlobalArray globalArray) {
        globalArrays.add(globalArray);
    }

    public IRSymbolTable getSymbolTable() {
        return irSymbolTable;
    }

    public void setUnCertain() {
        for (int i = 0; i < globalArrays.size(); i++) {
            globalArrays.get(i).unCertain();
        }
        for (int i = 0; i < globalVars.size(); i++) {
            globalVars.get(i).unCertain();
        }
    }

    public void setCertain() {
        for (int i = 0; i < globalArrays.size(); i++) {
            globalArrays.get(i).certain();
        }
        for (int i = 0; i < globalVars.size(); i++) {
            globalVars.get(i).certain();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("declare i32 @getint()\ndeclare void @putint(i32)\ndeclare void @putch(i32)\ndeclare void @putstr(i8*)\n");
        for (int i = 0; i < globalVars.size(); i++) {
            sb.append(globalVars.get(i));
        }
        for (int i = 0; i < globalArrays.size(); i++) {
            sb.append(globalArrays.get(i));
        }
        for (int i = 0; i < functions.size(); i++) {
            sb.append(functions.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    public ArrayList<IRFunction> getFunctions() {
        return functions;
    }

    public ArrayList<GlobalVariable> getGlobalVars() {
        return globalVars;
    }

    public ArrayList<GlobalArray> getGlobalArrays() {
        return globalArrays;
    }
}
