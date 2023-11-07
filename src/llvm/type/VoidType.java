package llvm.type;

public class VoidType implements Type{
    public VoidType() {

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
        return false;
    }

    @Override
    public boolean isPointer() {
        return false;
    }

    @Override
    public String toString() {
        return "void";
    }
}
