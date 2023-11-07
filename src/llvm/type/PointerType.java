package llvm.type;

public class PointerType implements Type{
    private Type pointType;
    public PointerType(Type pointType) {
        this.pointType = pointType;
    }

    public Type getPointType() {
        return pointType;
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
        return true;
    }

    @Override
    public String toString() {
        if (pointType instanceof PointerType) {
            return pointType + " *";
        }
        return pointType + "*";
    }
}
