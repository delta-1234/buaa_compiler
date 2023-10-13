package symbolTable;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
    private static ArrayList<SymbolTable> generalTable = new ArrayList<>();
    public static int curId = -1;
    public int id;            // 当前符号表的id
    public int fatherId;    // 当前符号表的父id
    public HashMap<String, Symbol> directory;
    public String funcName;

    public boolean isFor;

    public SymbolTable(int id, int fatherId) {
        this.id = id;
        this.fatherId = fatherId;
        directory = new HashMap<>();
        funcName = "";
        if (fatherId != -1 && generalTable.get(fatherId).isFor) {
            isFor = true;
        } else {
            isFor = false;
        }
    }

    public Symbol getFatherFunc() {
        return generalTable.get(fatherId).directory.get(funcName);
    }

    public static SymbolTable getCurrentTable() {
        return generalTable.get(curId);
    }

    public static SymbolTable getNextTable() {
        if (generalTable.size() <= curId+1) {
            generalTable.add(new SymbolTable(curId + 1, curId));
        }
        return generalTable.get(curId+1);
    }

    public static void addTable() {
        curId++;
        if (generalTable.size() <= curId) {
            generalTable.add(new SymbolTable(curId, curId - 1));
        }
    }

    public static void deleteTable() {
        generalTable.remove(curId);
        curId--;
    }

    public static Symbol search(String name, boolean curOnly, boolean isVar) {
        if (curOnly) {
            return generalTable.get(curId).directory.getOrDefault(name, null);
        }
        int i = curId;
        while (i != -1) {
            if (generalTable.get(i).directory.containsKey(name)) {
                if (isVar && generalTable.get(i).directory.get(name) instanceof Variate) {
                    return generalTable.get(i).directory.get(name);
                } else if (!isVar && generalTable.get(i).directory.get(name) instanceof Function) {
                    return generalTable.get(i).directory.get(name);
                }
            }
            i = generalTable.get(i).fatherId;
        }
        return null;
    }
}
