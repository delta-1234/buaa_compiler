package backend;

import llvm.type.ArrayType;
import llvm.type.PointerType;
import llvm.value.constant.ConstInt;
import llvm.value.constant.GlobalArray;
import llvm.value.constant.GlobalVariable;
import util.IRBuilder;
import util.Parser;

import java.util.ArrayList;

public class GlobalData {
    private ArrayList<GlobalVariable> globalVariables;
    private ArrayList<GlobalArray> globalArrays;

    public GlobalData() {
        globalVariables = IRBuilder.module.getGlobalVars();
        globalArrays = IRBuilder.module.getGlobalArrays();
    }

    public void build() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(".data\n");
        for (GlobalVariable globalVariable : globalVariables) {
            sb.append(globalVariable.getName());
            sb.append(": .word ");
            sb.append(((ConstInt) globalVariable.getValue()).getValue());
            sb.append("\n");
        }
        for (GlobalArray globalArray : globalArrays) {
            sb.append(globalArray.getName());
            sb.append(": ");
            ArrayType arrayType = (ArrayType) ((PointerType) globalArray.getType()).getPointType();
            int x = -1;
            for (int i = 0; i < arrayType.getMaxSize(); i++) {
                if (globalArray.getValue(i) instanceof ConstInt constInt &&
                    constInt.getValue() != 0) {
                    x = i;
                }
            }
            if (x == -1) {
                sb.append(".space ").append(arrayType.getMaxSize() * 4);
                sb.append("\n");
            } else {
                sb.append(".word ");
                for (int i = 0; i <= x; i++) {
                    ConstInt constInt = (ConstInt) globalArray.getValue(i);
                    sb.append(constInt.getValue());
                    if (i != x) {
                        sb.append(",");
                    }
                }
                sb.append("\n");
                if (x != arrayType.getMaxSize() - 1) {
                    sb.append("\t.space ").append((arrayType.getMaxSize() - x - 1) * 4);
                    sb.append("\n");
                }
            }
        }
        for (String str : Mips.formatStr.keySet()) {
            sb.append(Mips.formatStr.get(str));
            sb.append(": .asciiz \"");
            sb.append(str.replace("\n", "\\n"));
            sb.append("\"");
            sb.append("\n");
        }
        return sb.toString();
    }
}
