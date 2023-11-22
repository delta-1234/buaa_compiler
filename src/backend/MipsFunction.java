package backend;

import llvm.value.Value;
import llvm.value.constant.IRFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MipsFunction {
    private ArrayList<MipsBlock> blocks;
    private IRFunction irFunction;
    public boolean[] register;
    public Value[] regToVar;
    public HashMap<Value, Integer> stack;
    public int stackP;
    public HashMap<Value, Integer> varToReg;
    public HashMap<Value, HashSet<Value>> clashGraph;
    private StringBuilder sb;
    //函数返回参数$v0-$v1(2-3)
    //函数参数寄存器$a0-$a3(4-7)，共4个
    //全局寄存器$s0-$s7(16-23)，共8个
    //局部寄存器$t0-$t7(8-15)，$t8-$t9(24-25)，共10个
    //堆指针$gp(28)
    //栈指针$sp(29)
    //函数栈帧指针$fp(30)
    //函数返回地址$ra(31)
    /*
    |   arg6    | <-fp
    |   arg5    |
    |   ....    |
    |   arg4    |
    |   local   |
     */

    public MipsFunction(IRFunction irFunction) {
        blocks = new ArrayList<>();
        this.irFunction = irFunction;
        register = new boolean[32];
        for (int i = 0; i < 28; i++) {
            register[i] = false;
        }
        register[29] = true;
        register[30] = true;
        register[31] = true;
        regToVar = new Value[32];
        stack = new HashMap<>();
        varToReg = new HashMap<>();
        stackP = 0;
        clashGraph = irFunction.getClashGraph();
        sb = new StringBuilder();
    }

    public void build() {
        //分配全局寄存器
        HashMap<Value, HashSet<Value>> clashMap = new HashMap<>();
        for (Value v : irFunction.getClashGraph().keySet()) {
            clashMap.put(v, new HashSet<>());
            for (Value v1 : irFunction.getClashGraph().get(v)) {
                clashMap.get(v).add(v1);
            }
        }
        ArrayList<Value> order = new ArrayList<>();
        while (true) {
            boolean flag = false;
            for (Value v : clashMap.keySet()) {
                if (clashMap.get(v).size() < 8) {
                    order.add(v);
                    flag = true;
                    for (Value v1 : clashMap.get(v)) {
                        clashMap.get(v1).remove(v);
                    }
                    clashMap.remove(v);
                    break;
                }
            }
            if (flag) {
                continue;
            }
            if (clashMap.size() == 0) {
                break;
            }
            Value maxClash = null;
            for (Value v : clashMap.keySet()) {
                if (maxClash == null) {
                    maxClash = v;
                }
                if (clashMap.get(v).size() > clashMap.get(maxClash).size()) {
                    maxClash = v;
                }
            }
            for (Value v1 : clashMap.get(maxClash)) {
                clashMap.get(v1).remove(maxClash);
            }
            clashMap.remove(maxClash);
        }
        for (int i = order.size() - 1; i >= 0; i--) {
            boolean[] x = new boolean[8];
            for (Value v : irFunction.getClashGraph().get(order.get(i))) {
                if (varToReg.containsKey(v)) {
                    x[varToReg.get(v) - 16] = true;
                }
            }
            for (int j = 0; j < 8; j++) {
                if (!x[j]) {
                    varToReg.put(order.get(i), j + 16);
                    break;
                }
            }
        }
        for (int i = 0; i < irFunction.getBBNum(); i++) {
            blocks.add(new MipsBlock(irFunction.getBB(i), this, irFunction.getBB(i + 1)));
            blocks.get(i).build();
        }
    }

    @Override
    public String toString() {
        sb.append(irFunction.getName());
        sb.append(":\n");
        if (irFunction.getName().equals("main")) {
            sb.append("move $fp, $sp\n"); //设置栈帧
        }
        for (int i = 0; i < blocks.size(); i++) {
            sb.append(blocks.get(i));
        }
        return sb.toString();
    }
}
