package llvm.type;

import java.util.ArrayList;

public class ArrayType implements Type {

    private int dim;
    private ArrayList<Integer> dimSize; //从0开始
    private ArrayList<Integer> P;
    private int maxSize;

    public ArrayType(int dim, ArrayList<Integer> dimSize) {
        this.dim = dim;
        this.dimSize = dimSize;
        P = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(1);
        for (int i = dimSize.size() - 2; i >= 0; i--) {
            temp.add(temp.get(dimSize.size() - i - 2) * dimSize.get(i + 1));
        }
        for (int i = 0; i < temp.size(); i++) {
            P.add(temp.get(temp.size() - i - 1));
        }
        maxSize = 1;
        for (int i = 0; i < dimSize.size(); i++) {
            maxSize *= dimSize.get(i);
        }
    }

    public int getDim() {
        return dim;
    }

    public int getDimSize(int dim) {
        return dimSize.get(dim);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getP(int i) {
        return P.get(i);
    }

    public int getOffset(ArrayList<Integer> index) {
        int loc = 0;
        for (int i = 0; i < index.size(); i++) {
            loc += (index.get(i) * P.get(i));
        }
        return loc;
    }

    public Type getSonType() {
        if (dim == 1) {
            return new IntegerType(32);
        }
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 1; i < dimSize.size(); i++) {
            temp.add(dimSize.get(i));
        }
        return new ArrayType(dim - 1, temp);
    }

    @Override
    public boolean isInteger() {
        return false;
    }

    @Override
    public boolean isArray() {
        return true;
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
        return getMaxSize() * 4;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dim; i++) {
            sb.append("[" + dimSize.get(i) + " x ");
        }
        sb.append("i32");
        for (int i = 0; i < dim; i++) {
            sb.append("]");
        }
        return sb.toString();
    }
}
