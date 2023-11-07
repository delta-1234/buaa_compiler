package llvm.value;

import llvm.Use;
import llvm.type.Type;

import java.util.ArrayList;
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
}
