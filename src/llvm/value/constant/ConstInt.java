package llvm.value.constant;

import llvm.type.IntegerType;
import llvm.type.Type;

public class ConstInt extends Constant {
    private int result;
    private static final ConstInt zero = new ConstInt("0", new IntegerType(32), 0);

    public ConstInt(String name, Type type, int value) {
        super(name, type);
        this.result = value;
        setIdent(name);
    }

    public int getValue() {
        return result;
    }

    public void setValue(int value) {
        result = value;
    }

    public ConstInt neg() {
        return new ConstInt(Integer.toString(-result), getType(), -result);
    }

    public ConstInt add(ConstInt c) {
        int temp = result + c.getValue();
        return new ConstInt(Integer.toString(temp), getType(), temp);
    }

    public ConstInt sub(ConstInt c) {
        int temp = result - c.getValue();
        return new ConstInt(Integer.toString(temp), getType(), temp);
    }

    public ConstInt mul(ConstInt c) {
        int temp = result * c.getValue();
        return new ConstInt(Integer.toString(temp), getType(), temp);
    }

    public ConstInt div(ConstInt c) {
        int temp = result / c.getValue();
        return new ConstInt(Integer.toString(temp), getType(), temp);
    }

    public ConstInt mod(ConstInt c) {
        int temp = result % c.getValue();
        return new ConstInt(Integer.toString(temp), getType(), temp);
    }

    public static ConstInt getZero() {
        return zero;
    }

    @Override
    public String toString() {
        return "i" + ((IntegerType) getType()).getBit() + " " + result;
    }
}
