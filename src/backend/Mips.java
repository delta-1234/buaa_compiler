package backend;

import llvm.value.BasicBlock;
import llvm.value.Value;
import llvm.value.constant.ConstInt;
import llvm.value.constant.IRFunction;
import llvm.value.instruction.CallIns;
import llvm.value.instruction.Instruction;
import util.IRBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class Mips {
    private GlobalData globalData;
    private ArrayList<MipsFunction> mipsFunctions;
    public HashMap<Value, Integer> heap;
    private StringBuilder sb;
    public static HashMap<String, String> formatStr = new HashMap<>();

    public Mips() {
        globalData = new GlobalData();
        mipsFunctions = new ArrayList<>();
        ArrayList<IRFunction> functions = IRBuilder.module.getFunctions();
        for (int i = 0; i < functions.size(); i++) {
            mipsFunctions.add(new MipsFunction(functions.get(i)));
        }
        heap = new HashMap<>();
        sb = new StringBuilder();
        int count = 0;
        for (IRFunction function : functions) {
            for (BasicBlock BB : function.getBasicBlocks()) {
                StringBuilder temp = new StringBuilder();
                for (Instruction ins : BB.getInstructions()) {
                    if (ins instanceof CallIns callIns &&
                        callIns.getFunc().getName().equals("putch")) {
                        ConstInt constInt = (ConstInt) callIns.getRealParams().get(0);
                        temp.append((char) constInt.getValue());
                    } else {
                        if (temp.length() > 1 && !formatStr.containsKey(temp.toString())) {
                            formatStr.put(temp.toString(), "str" + count);
                            count++;
                        }
                        temp = new StringBuilder();
                    }
                }
            }
        }
    }

    public void build() {
        globalData.build();
        for (int i = 0; i < mipsFunctions.size(); i++) {
            mipsFunctions.get(i).build();
        }
    }

    @Override
    public String toString() {
        sb.append(globalData);
        sb.append("\n.text\n");
        if (mipsFunctions.size() > 1) {
            sb.append("j main\n\n");
        }
        for (int i = 0; i < mipsFunctions.size(); i++) {
            sb.append(mipsFunctions.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    public int calFinalCycle() {
        return 0;
    }
}
