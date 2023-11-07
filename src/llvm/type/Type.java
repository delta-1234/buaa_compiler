package llvm.type;

public interface Type {
    boolean isInteger();
    boolean isArray();
    boolean isFunction();

    boolean isPointer();
}
