package llvm;

import llvm.value.Value;

import java.util.ArrayList;
import java.util.HashMap;

public class IRSymbolTable {
    private ArrayList<HashMap<String, Value>> symbolTable;

    public IRSymbolTable() {
        symbolTable = new ArrayList<>();
    }

    public Value search(String name) {
        for (int i = symbolTable.size()-1; i >= 0; i--) {
            if (symbolTable.get(i).containsKey(name)) {
                return symbolTable.get(i).get(name);
            }
        }
        return null;
    }

    public void addSymbol(Value value) {
        symbolTable.get(symbolTable.size()-1).put(value.getName(), value);
    }

    public void addTable() {
        symbolTable.add(new HashMap<>());
    }

    public void deleteTable() {
        symbolTable.remove(symbolTable.size()-1);
    }
}
