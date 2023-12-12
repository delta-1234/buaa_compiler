package llvm;

import llvm.value.constant.GlobalArray;
import llvm.value.constant.GlobalVariable;
import llvm.value.constant.IRFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class IRModule {
    private IRSymbolTable irSymbolTable;
    private ArrayList<IRFunction> functions;
    private ArrayList<GlobalVariable> globalVars;
    private ArrayList<GlobalArray> globalArrays;
    public static HashSet<IRFunction> noneRecursive;

    public IRModule() {
        irSymbolTable = new IRSymbolTable();
        functions = new ArrayList<>();
        globalVars = new ArrayList<>();
        globalArrays = new ArrayList<>();
        noneRecursive = new HashSet<>();
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
        sb.append(
            "declare i32 @getint()\ndeclare void @putint(i32)\ndeclare void @putch(i32)\ndeclare void @putstr(i8*)\n");
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

    private void calNoRecursive() {
        ArrayList<HashSet<Integer>> graph = new ArrayList<>();
        HashMap<String, Integer> nameToFUnc = new HashMap<>();
        HashMap<String, Integer> refCount = new HashMap<>();
        for (int i = 0; i < functions.size(); i++) {
            for (String name : functions.get(i).callFunc().keySet()) {
                if (!refCount.containsKey(name)) {
                    refCount.put(name, 0);
                }
                int t = refCount.get(name);
                refCount.put(name, t + 1);
            }
        }
        for (int i = 0; i < functions.size(); i++) {
            nameToFUnc.put(functions.get(i).getName(), i);
        }
        for (int i = 0; i < functions.size() - 1; i++) {
            graph.add(new HashSet<>());
            for (String name : functions.get(i).callFunc().keySet()) {
                graph.get(i).add(nameToFUnc.get(name));
            }
        }
        HashSet<IRFunction> temp = new HashSet<>();
        for (int i = 0; i < functions.size() - 1; i++) {
            HashSet<Integer> visited = new HashSet<>();
            if (dfs(graph, i, visited)) {
                temp.add(functions.get(i));
            }
        }
        //不是所有函数展开都有利于缩减时间，规定小函数，同时调用次数少的函数才展开
        for (IRFunction func : temp) {
            int insCount = 0;
            for (int i = 0; i < func.getBasicBlocks().size(); i++) {
                insCount += func.getBasicBlocks().get(i).getInstructions().size();
            }
            //总指令条数小于100条且引用次数小于10才展开
            if (insCount < 50 && refCount.get(func.getName()) < 3) {
                noneRecursive.add(func);
            }
        }
    }

    private boolean dfs(ArrayList<HashSet<Integer>> graph, int begin, HashSet<Integer> visited) {
        if (visited.contains(begin)) {
            return false;
        }
        visited.add(begin);
        for (Integer i : graph.get(begin)) {
            if (visited.contains(i)) {
                return false;
            }
            if (!dfs(graph, i, visited)) {
                return false;
            } else {
                visited.add(i);
            }
        }
        return true;
    }

    public void funcInLine() {
        calNoRecursive();
        for (int i = 0; i < functions.size(); i++) {
            functions.get(i).inLine(null, null);
        }
        for (IRFunction func : noneRecursive) {
            functions.remove(func);
        }
    }
}
