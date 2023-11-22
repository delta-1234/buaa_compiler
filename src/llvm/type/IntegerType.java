package llvm.type;

public class IntegerType implements Type {
    private int bit;

    public IntegerType(int bit) {
        this.bit = bit;
    }

    public int getBit() {
        return bit;
    }

    @Override
    public boolean isInteger() {
        return true;
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
    public int getSize() {
        return bit / 8;
    }

    @Override
    public String toString() {
        return "i" + bit;
    }
}
