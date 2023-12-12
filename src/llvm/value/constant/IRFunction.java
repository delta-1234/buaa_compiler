package llvm.value.constant;

import llvm.IRModule;
import llvm.Use;
import llvm.type.FunctionType;
import llvm.type.IntegerType;
import llvm.type.PointerType;
import llvm.type.Type;
import llvm.type.VoidType;
import llvm.value.BasicBlock;
import llvm.value.User;
import llvm.value.Value;
import llvm.value.instruction.AllocaIns;
import llvm.value.instruction.BrIns;
import llvm.value.instruction.CalculateIns;
import llvm.value.instruction.CallIns;
import llvm.value.instruction.CmpIns;
import llvm.value.instruction.GetIns;
import llvm.value.instruction.Instruction;
import llvm.value.instruction.LoadIns;
import llvm.value.instruction.Operation;
import llvm.value.instruction.Phi;
import llvm.value.instruction.RetIns;
import llvm.value.instruction.StoreIns;
import llvm.value.instruction.UnaryIns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class IRFunction extends GlobalValue {
    private ArrayList<BasicBlock> basicBlocks;
    private FunctionType functionType;
    private HashMap<Value, HashSet<Value>> clashGraph;
    private HashMap<BasicBlock, HashSet<BasicBlock>> fatherToSon; //控制流程图（Control Flow Graph）
    private HashMap<BasicBlock, HashSet<BasicBlock>> sonToFather;
    private HashMap<BasicBlock, HashSet<BasicBlock>> domTree;

    public IRFunction(String name, Type type) {
        super(name, type);
        setIdent("@" + name);
        functionType = (FunctionType) type;
        basicBlocks = new ArrayList<>();
        clashGraph = new HashMap<>();
        fatherToSon = new HashMap<>();
        sonToFather = new HashMap<>();
        domTree = new HashMap<>();
    }

    public BasicBlock getBB(int i) { //超出index返回null
        if (i >= basicBlocks.size()) {
            return null;
        }
        return basicBlocks.get(i);
    }

    public BasicBlock getLastBB() {
        return basicBlocks.get(basicBlocks.size() - 1);
    }

    public int getBBNum() {
        return basicBlocks.size();
    }

    public void addBB(BasicBlock basicBlock) {
        basicBlocks.add(basicBlock);
    }

    public void deleteDead() {
        if (getLastBB().getLastIns() == null || !(getLastBB().getLastIns() instanceof RetIns)) {
            getLastBB().addIns(
                new RetIns("", new VoidType(), getLastBB(), Operation.RET));
        }
        for (int i = 0; i < basicBlocks.size(); i++) {
            basicBlocks.get(i).deleteDead();
        }
        while (true) {
            boolean flag = true;
            for (int i = 0; i < basicBlocks.size(); i++) {
                if (basicBlocks.get(i).deleteUseless()) {
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        if (basicBlocks.size() != 1) {
            ArrayList<Integer> rec = new ArrayList<>();
            for (int k = 0; true; k++) {
                HashSet<BasicBlock> hasFather = new HashSet<>();
                for (int i = 0; i < basicBlocks.size(); i++) {
                    Instruction ins = basicBlocks.get(i).getLastIns();
                    if (!(ins instanceof BrIns brIns)) {
                        continue;
                    }
                    if (brIns.getDest() != null) {
                        hasFather.add(brIns.getDest());
                        BasicBlock dest = brIns.getDest();
                        if (dest.getInstructions().size() != 1) {
                            continue;
                        }
                        if (!(dest.getLastIns() instanceof BrIns temp)) {
                            continue;
                        }
                        if (temp.getDest() != null) {
                            brIns.setDest(temp.getDest());
                        } else {
                            brIns.setDest(null);
                            brIns.setTrueBB(temp.getTrueBB());
                            brIns.setFalseBB(temp.getFalseBB());
                            brIns.setCond(temp.getCond());
                        }
                    } else {
                        hasFather.add(brIns.getTrueBB());
                        hasFather.add(brIns.getFalseBB());
                        BasicBlock trueBB = brIns.getTrueBB();
                        BasicBlock falseBB = brIns.getFalseBB();
                        BasicBlock temp;
                        if (trueBB.equals(falseBB)) {
                            brIns.setDest(trueBB);
                            brIns.removeValue();
                            continue;
                        }
                        if (trueBB.getInstructions().size() == 1 &&
                            trueBB.getLastIns() instanceof BrIns) {
                            temp = ((BrIns) trueBB.getLastIns()).getDest();
                            if (temp != null) {
                                brIns.setTrueBB(temp);
                            }
                        }
                        if (falseBB.getInstructions().size() != 1) {
                            continue;
                        }
                        if (!(falseBB.getLastIns() instanceof BrIns)) {
                            continue;
                        }
                        temp = ((BrIns) falseBB.getLastIns()).getDest();
                        if (temp != null) {
                            brIns.setFalseBB(temp);
                        }
                    }
                }
                for (int i = 1; i < basicBlocks.size(); i++) {
                    if (!hasFather.contains(basicBlocks.get(i))) {
                        ArrayList<Instruction> ins = basicBlocks.get(i).getInstructions();
                        for (int j = 0; j < ins.size(); j++) {
                            ins.get(j).destroy();
                        }
                        basicBlocks.remove(i);
                        i--;
                    }
                }
                rec.add(basicBlocks.size());
                if (k > 3 && Objects.equals(rec.get(k - 1), rec.get(k - 2)) &&
                    Objects.equals(rec.get(k - 2), rec.get(k - 3))) {
                    break;
                }
            }
        }
        while (true) {
            boolean flag = true;
            for (int i = 0; i < basicBlocks.size(); i++) {
                if (basicBlocks.get(i).deleteUseless()) {
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    public void buildClashGraph() {
        for (int i = 0; i < basicBlocks.size(); i++) {
            basicBlocks.get(i).removeUnaryIns();
        }
        for (int i = 0; i < basicBlocks.size(); i++) {
            basicBlocks.get(i).calUseDef();
        }
        HashMap<BasicBlock, Integer> BBtoNum = new HashMap<>();
        for (int i = 0; i < basicBlocks.size(); i++) {
            if (basicBlocks.get(i).getLastIns() instanceof BrIns brIns) {
                if (brIns.getDest() != null) {
                    basicBlocks.get(i).getSucceed().add(brIns.getDest());
                } else {
                    basicBlocks.get(i).getSucceed().add(brIns.getTrueBB());
                    basicBlocks.get(i).getSucceed().add(brIns.getFalseBB());
                }
            }
            BBtoNum.put(basicBlocks.get(i), i);
        }
        ArrayList<HashSet<Value>> inSet = new ArrayList<>();
        ArrayList<HashSet<Value>> lastInSet = new ArrayList<>();
        ArrayList<HashSet<Value>> outSet = new ArrayList<>();
        for (int i = 0; i < basicBlocks.size(); i++) {
            inSet.add(new HashSet<>());
            lastInSet.add(new HashSet<>());
            outSet.add(new HashSet<>());
        }
        HashMap<Value, HashSet<Integer>> valueToBB = new HashMap<>();
        while (true) {
            for (int i = basicBlocks.size() - 1; i >= 0; i--) {
                outSet.get(i).clear();
                for (BasicBlock succeed : basicBlocks.get(i).getSucceed()) {
                    outSet.get(i).addAll(inSet.get(BBtoNum.get(succeed)));
                }
                inSet.get(i).clear();
                inSet.get(i).addAll(basicBlocks.get(i).getUseValue());
                for (Value value : outSet.get(i)) {
                    if (!basicBlocks.get(i).getDefValue().contains(value)) {
                        inSet.get(i).add(value);
                    }
                }
            }
            if (lastInSet.equals(inSet)) {
                break;
            }
            lastInSet.clear();
            valueToBB.clear();
            for (int i = 0; i < inSet.size(); i++) {
                lastInSet.add(new HashSet<>());
                for (Value v : inSet.get(i)) {
                    lastInSet.get(i).add(v);
                    if (!valueToBB.containsKey(v)) {
                        valueToBB.put(v, new HashSet<>());
                    }
                    valueToBB.get(v).add(i);
                }
            }
        }
        HashSet<Value> args = new HashSet<>();
        for (int i = 0; i < functionType.getParamNum(); i++) {
            args.add(functionType.getParam(i));
        }
        for (int i = 0; i < inSet.size(); i++) {
            HashSet<Value> temp = new HashSet<>(inSet.get(i));
            temp.addAll(basicBlocks.get(i).getDefined());
            for (Value v1 : temp) {
                for (Value v2 : temp) {
                    if (!valueToBB.containsKey(v2) || valueToBB.get(v2).size() < 2 ||
                        v1.equals(v2) || args.contains(v2)) {
                        continue;
                    }
                    if (!valueToBB.containsKey(v1) || valueToBB.get(v1).size() < 2 ||
                        v1.equals(v2) || args.contains(v1)) {
                        continue;
                    }
                    if (!clashGraph.containsKey(v1)) {
                        clashGraph.put(v1, new HashSet<>());
                    }
                    if (!clashGraph.containsKey(v2)) {
                        clashGraph.put(v2, new HashSet<>());
                    }
                    clashGraph.get(v1).add(v2);
                    clashGraph.get(v2).add(v1);
                }
            }
        }
    }

    public void buildCFG() {
        for (int i = 0; i < basicBlocks.size(); i++) {
            if (!fatherToSon.containsKey(basicBlocks.get(i))) {
                fatherToSon.put(basicBlocks.get(i), new HashSet<>());
            }
            if (!sonToFather.containsKey(basicBlocks.get(i))) {
                sonToFather.put(basicBlocks.get(i), new HashSet<>());
            }
            if (basicBlocks.get(i).getLastIns() instanceof BrIns brIns) {
                if (brIns.getDest() != null) {
                    fatherToSon.get(basicBlocks.get(i)).add(brIns.getDest());
                    if (!sonToFather.containsKey(brIns.getDest())) {
                        sonToFather.put(brIns.getDest(), new HashSet<>());
                    }
                    sonToFather.get(brIns.getDest()).add(basicBlocks.get(i));
                } else {
                    fatherToSon.get(basicBlocks.get(i)).add(brIns.getTrueBB());
                    if (!sonToFather.containsKey(brIns.getTrueBB())) {
                        sonToFather.put(brIns.getTrueBB(), new HashSet<>());
                    }
                    sonToFather.get(brIns.getTrueBB()).add(basicBlocks.get(i));
                    fatherToSon.get(basicBlocks.get(i)).add(brIns.getFalseBB());
                    if (!sonToFather.containsKey(brIns.getFalseBB())) {
                        sonToFather.put(brIns.getFalseBB(), new HashSet<>());
                    }
                    sonToFather.get(brIns.getFalseBB()).add(basicBlocks.get(i));
                }
            }
        }
    }

    private void calDom() {
        for (int i = 0; i < basicBlocks.size(); i++) {
            basicBlocks.get(i).getDom().add(basicBlocks.get(0));
        }
        while (true) {
            boolean unChange = true;
            for (BasicBlock BB : basicBlocks) {
                //某基本块所有前驱的dom的交集
                HashSet<BasicBlock> andDom = new HashSet<>();
                boolean flag = true;
                for (BasicBlock prev : sonToFather.get(BB)) {
                    if (flag) {
                        //拷贝
                        andDom.addAll(prev.getDom());
                        flag = false;
                        continue;
                    }
                    //求交集
                    andDom.retainAll(prev.getDom());
                }
                andDom.add(BB); //加上自身
                if (!andDom.equals(BB.getDom())) {
                    unChange = false;
                    BB.getDom().clear();
                    BB.getDom().addAll(andDom);
                }
            }
            if (unChange) {
                break;
            }
        }
        for (BasicBlock BB : basicBlocks) {
            for (BasicBlock i : BB.getDom()) {
                //首先满足严格支配BB
                if (!BB.isStrictDom(i)) {
                    continue;
                }
                //不严格支配任何严格支配 BB 的节点的节点
                boolean flag = true;
                for (BasicBlock j : BB.getDom()) {
                    if (j.isStrictDom(i) && BB.isStrictDom(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    BB.setImmediateDom(i);
                    if (!domTree.containsKey(i)) {
                        domTree.put(i, new HashSet<>());
                    }
                    domTree.get(i).add(BB);
                    break;
                }
            }
        }
    }

    //计算支配边界
    private void calDF() {
        calDom();
        for (BasicBlock a : basicBlocks) {
            for (BasicBlock b : fatherToSon.get(a)) {
                BasicBlock x = a;
                while (x != null && !b.isStrictDom(x)) {
                    x.getDF().add(b);
                    x = x.getImmediateDom();
                }
            }
        }
    }

    //插入phi指令
    public void insertPhi() {
        calDF();
        HashSet<Value> varSet = new HashSet<>();
        for (BasicBlock BB : basicBlocks) {
            for (Value v : BB.getInstructions()) {
                if (v instanceof StoreIns storeIns &&
                    storeIns.getPointer() instanceof AllocaIns allocaIns &&
                    allocaIns.getDefType() instanceof IntegerType) {
                    varSet.add(storeIns.getPointer());
                }
                if (v instanceof AllocaIns allocaIns &&
                    allocaIns.getDefType() instanceof IntegerType) {
                    varSet.add(v);
                }
            }
        }
        for (Value v : varSet) {
            HashSet<BasicBlock> F = new HashSet<>();
            HashSet<BasicBlock> defs = new HashSet<>();
            User user = (User) v;
            for (Use u : user.getOperands()) {
                Value d = u.getValue();
                BasicBlock b = ((Instruction) d).getParent();
                if (containBB(b)) {
                    defs.add(b);
                }
            }
            HashSet<BasicBlock> W = new HashSet<>(defs);
            while (!W.isEmpty()) {
                BasicBlock X = (BasicBlock) (W.toArray())[0];
                W.remove(X);
                for (BasicBlock Y : X.getDF()) {
                    if (!F.contains(Y)) {
                        Phi phi = new Phi("", new IntegerType(32), Y, Operation.PHI);
                        phi.addChoice(v);
                        Y.addEntry(phi);
                        F.add(Y);
                        if (!defs.contains(Y)) {
                            W.add(Y);
                        }
                    }
                }
            }
        }
        for (Value v : varSet) {
            AllocaIns allocaIns = (AllocaIns) v;
            ArrayList<Value> reachingDef = new ArrayList<>();
            reachingDef.add(ConstInt.getZero()); //初始到达定义为0
            varReName(basicBlocks.get(0), reachingDef, allocaIns);
        }
        ArrayList<Phi> phis = new ArrayList<>();
        for (int i = 0; i < basicBlocks.size(); i++) {
            phis.addAll(basicBlocks.get(i).getPhi());
        }
        for (int i = 0; i < phis.size(); i++) {
            boolean flag = true;
            for (Use u : phis.get(i).getUseList()) {
                if (!u.getUser().equals(phis.get(i))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                phis.get(i).destroy();
                phis.get(i).getParent().getInstructions().remove(phis.get(i));
            }
        }
    }

    private boolean containBB(BasicBlock basicBlock) {
        HashSet<BasicBlock> blockHashSet = new HashSet<>(basicBlocks);
        if (blockHashSet.contains(basicBlock)) {
            return true;
        }
        return false;
    }

    private void varReName(BasicBlock BB, ArrayList<Value> reachingDef, AllocaIns alloc) {
        int count = 0;
        //DFS遍历整个支配树
        for (int i = 0; i < BB.getInstructions().size(); i++) {
            Instruction ins = BB.getInstructions().get(i);
            if (ins instanceof AllocaIns allocaIns) {
                //alloca直接删除
                if (allocaIns.getDefType() instanceof IntegerType) {
                    ins.destroy();
                    if (BB.getFather().getBasicBlocks().get(0).equals(BB) &&
                        i < BB.getFather().getParamNum() * 2) {
                        continue; //函数形参保留，为了后端接口
                    }
                    ins.getParent().getInstructions().remove(ins);
                    i--;
                }
            } else if (ins instanceof LoadIns loadIns && loadIns.getPointer().equals(alloc)) {
                //load 指令：将所有其他指令中对该指令的使用替换为对该变量到达定义的使用，删除
                if (!(loadIns.getPointer() instanceof AllocaIns allocaIns)) {
                    continue;
                }
                if (!(allocaIns.getDefType() instanceof IntegerType)) {
                    continue;
                }
                Value v = reachingDef.get(reachingDef.size() - 1);
                replaceUsed(loadIns, v);
                loadIns.destroy();
                loadIns.getParent().getInstructions().remove(ins);
                i--;
            } else if (ins instanceof StoreIns storeIns && storeIns.getPointer().equals(alloc)) {
                //store 指令：更新该变量的到达定义，删除
                if (storeIns.getPointer() instanceof AllocaIns allocaIns &&
                    allocaIns.getDefType() instanceof IntegerType) {
                    reachingDef.add(storeIns.getValue());
                    storeIns.destroy();
                    count++;
                    if (BB.getFather().getBasicBlocks().get(0).equals(BB) &&
                        i < BB.getFather().getParamNum() * 2) {
                        continue; //函数形参保留，为了后端接口
                    }
                    storeIns.getParent().getInstructions().remove(ins);
                    i--;
                }
            } else if (ins instanceof Phi phi && phi.getOldValue().equals(alloc)) {
                //Phi：更新原变量的到达定义
                reachingDef.add(phi);
                phi.getOperands().remove(phi.getOldValue());
                count++;
            }
        }
        for (BasicBlock succeed : fatherToSon.get(BB)) {
            for (Instruction ins : succeed.getInstructions()) {
                if (ins instanceof Phi phi && phi.getOldValue().equals(alloc)) {
                    Value v = reachingDef.get(reachingDef.size() - 1);
                    phi.setChoice(v, BB);
                }
            }
        }
        if (domTree.containsKey(BB)) {
            for (BasicBlock b : domTree.get(BB)) {
                varReName(b, reachingDef, alloc);
            }
        }
        for (int i = 0; i < count; i++) {
            reachingDef.remove(reachingDef.size() - 1);
        }
    }

    private void replaceUsed(LoadIns loadIns, Value reachingDef) {
        for (Use u : loadIns.getUseList()) {
            Instruction useIns = (Instruction) u.getUser();
            if (useIns instanceof BrIns brIns) {
                brIns.setValue(reachingDef);
            } else if (useIns instanceof CalculateIns calculateIns) {
                if (calculateIns.getOp1().equals(loadIns)) {
                    calculateIns.setOp1(reachingDef);
                }
                if (calculateIns.getOp2().equals(loadIns)) {
                    calculateIns.setOp2(reachingDef);
                }
            } else if (useIns instanceof CallIns callIns) {
                for (int i = 0; i < callIns.getRealParams().size(); i++) {
                    if (callIns.getRealParams().get(i).equals(loadIns)) {
                        callIns.getRealParams().set(i, reachingDef);
                        Use.getInstance(reachingDef, callIns);
                    }
                }
            } else if (useIns instanceof CmpIns cmpIns) {
                if (cmpIns.getOp1().equals(loadIns)) {
                    cmpIns.setOp1(reachingDef);
                }
                if (cmpIns.getOp2().equals(loadIns)) {
                    cmpIns.setOp2(reachingDef);
                }
            } else if (useIns instanceof GetIns getIns) {
                for (int i = 0; i < getIns.getIndex().size(); i++) {
                    if (getIns.getIndex().get(i).equals(loadIns)) {
                        getIns.getIndex().set(i, reachingDef);
                        Use.getInstance(reachingDef, getIns);
                    }
                }
            } else if (useIns instanceof Phi phi) {
                phi.replace(reachingDef, loadIns);
            } else if (useIns instanceof RetIns retIns) {
                retIns.setValue(reachingDef);
            } else if (useIns instanceof StoreIns storeIns) {
                storeIns.setValue(reachingDef);
            } else if (useIns instanceof UnaryIns unaryIns) {
                unaryIns.setValue(reachingDef);
            }
        }
    }

    public void deletePhi() {
        ArrayList<Phi> phis = new ArrayList<>();
        for (int i = 0; i < basicBlocks.size(); i++) {
            phis.addAll(basicBlocks.get(i).getPhi());
        }
        ArrayList<AllocaIns> allocas = new ArrayList<>();
        for (int i = 0; i < phis.size(); i++) {
            AllocaIns allocaIns = new AllocaIns("", new PointerType(new IntegerType(32)),
                basicBlocks.get(0), Operation.ALLOCA, false);
            basicBlocks.get(0).addEnd(allocaIns);
            allocas.add(allocaIns);
            LoadIns loadIns = new LoadIns("", new IntegerType(32), phis.get(i).getParent(),
                Operation.LOAD, phis.get(i), allocaIns);
            phis.get(i).getParent().addEntry(loadIns);
            phis.get(i).getParent().getInstructions().remove(phis.get(i));
        }
        for (int i = 0; i < phis.size(); i++) {
            phis.get(i).destroy();
            for (BasicBlock j : phis.get(i).getChoice().keySet()) {
                Value v = phis.get(i).getChoice().get(j);
                StoreIns storeIns = new StoreIns("", null, j, Operation.STORE,
                    phis.get(i).getChoice().get(j), allocas.get(i));
                j.addEnd(storeIns);
            }
        }
    }

    public void calSimplify() {
        while (true) {
            boolean flag = true;
            for (int i = 0; i < basicBlocks.size(); i++) {
                if (basicBlocks.get(i).calSimplify()) {
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    public void LVN() {
        while (true) {
            boolean flag = true;
            for (int i = 0; i < basicBlocks.size(); i++) {
                if (basicBlocks.get(i).LVN()) {
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    public HashMap<String, Integer> callFunc() {
        HashMap<String, Integer> calls = new HashMap<>();
        for (int i = 0; i < basicBlocks.size(); i++) {
            HashMap<String, Integer> temp = basicBlocks.get(i).callFunc();
            for (String name : temp.keySet()) {
                if (!calls.containsKey(name)) {
                    calls.put(name, 0);
                }
                int t = calls.get(name);
                calls.put(name, t + 1);
            }
        }
        return calls;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("define dso_local ");
        sb.append(functionType.getRetType());
        sb.append(" ");
        sb.append(getIdent());
        sb.append("(");
        for (int i = 0; i < functionType.getParamNum(); i++) {
            sb.append(functionType.getParam(i));
            if (i != functionType.getParamNum() - 1) {
                sb.append(", ");
            }
        }
        sb.append("){\n");
        for (int i = 0; i < basicBlocks.size(); i++) {
            sb.append(basicBlocks.get(i));
        }
        sb.append("}\n");
        return sb.toString();
    }

    public ArrayList<BasicBlock> getBasicBlocks() {
        return basicBlocks;
    }

    public int getParamNum() {
        return functionType.getParamNum();
    }

    public HashMap<Value, HashSet<Value>> getClashGraph() {
        return clashGraph;
    }

    public Type getRetType() {
        return functionType.getRetType();
    }

    public HashMap<BasicBlock, HashSet<BasicBlock>> getDomTree() {
        return domTree;
    }

    public ArrayList<BasicBlock> inLine(CallIns callIns, BasicBlock nextBB) {
        ArrayList<BasicBlock> newBasicBlock;
        AllocaIns allocaIns = null;
        if (callIns == null) {
            newBasicBlock = basicBlocks;
        } else {
            newBasicBlock = clone(callIns);
            if (callIns.getType() instanceof IntegerType) {
                allocaIns = new AllocaIns("", new PointerType(new IntegerType(32)),
                    newBasicBlock.get(0), Operation.ALLOCA, false);
                newBasicBlock.get(0).addEnd(allocaIns);
            }
        }
        for (int i = 0; i < newBasicBlock.size(); i++) {
            for (int j = 0; j < newBasicBlock.get(i).getInstructions().size(); j++) {
                Instruction ins = newBasicBlock.get(i).getInstructions().get(j);
                if (ins instanceof CallIns call &&
                    IRModule.noneRecursive.contains(call.getFunc())) {
                    BasicBlock newBB = new BasicBlock("", null, this);
                    //递归内联子函数
                    ArrayList<BasicBlock> temp = call.getFunc().inLine(call, newBB);
                    //将call改为br
                    BrIns brIns =
                        new BrIns("", null, newBasicBlock.get(i), Operation.BR, temp.get(0));
                    newBasicBlock.get(i).getInstructions().set(j, brIns);
                    //将此BB的剩余指令加入到newBB中
                    for (int k = j + 1; k < newBasicBlock.get(i).getInstructions().size(); k++) {
                        newBB.addIns(newBasicBlock.get(i).getInstructions().get(k));
                        newBasicBlock.get(i).getInstructions().get(k).setParent(newBB);
                    }
                    //插入内联的blocks
                    for (int k = i + 1; k < temp.size() + i + 1; k++) {
                        newBasicBlock.add(k, temp.get(k - i - 1));
                    }
                    //插入newBB
                    newBasicBlock.add(i + 1 + temp.size(), newBB);
                    //删除call后的代码
                    for (int k = j + 1; k < newBasicBlock.get(i).getInstructions().size(); k++) {
                        newBasicBlock.get(i).getInstructions().remove(k);
                        k--;
                    }
                    //重新遍历basicBlocks.get(i)
                    i--;
                    break;
                } else if (ins instanceof RetIns retIns) {
                    if (callIns == null) {
                        continue;
                    }
                    if (retIns.getValue() != null) {
                        StoreIns storeIns = new StoreIns("", null, newBasicBlock.get(i),
                            Operation.STORE, retIns.getValue(), allocaIns);
                        newBasicBlock.get(i).getInstructions().set(j, storeIns);
                        BrIns brIns =
                            new BrIns("", null, newBasicBlock.get(i), Operation.BR, nextBB);
                        retIns.destroy();
                        newBasicBlock.get(i).getInstructions().add(j + 1, brIns);
                    } else {
                        BrIns brIns =
                            new BrIns("", null, newBasicBlock.get(i), Operation.BR, nextBB);
                        retIns.destroy();
                        newBasicBlock.get(i).getInstructions().set(j, brIns);
                    }
                }
            }
        }
        if (allocaIns != null) {
            LoadIns loadIns =
                new LoadIns("", new IntegerType(32), nextBB, Operation.LOAD, allocaIns);
            nextBB.addIns(loadIns);
            callIns.replaceUsed(loadIns);
        }
        if (callIns != null) {
            callIns.destroy();
        }
        return newBasicBlock;
    }

    private ArrayList<BasicBlock> clone(CallIns callIns) {
        ArrayList<BasicBlock> temp = new ArrayList<>();
        HashMap<BasicBlock, BasicBlock> oldBBToNew = new HashMap<>();
        for (int i = 0; i < basicBlocks.size(); i++) {
            temp.add(new BasicBlock("", null, callIns.getParent().getFather()));
            oldBBToNew.put(basicBlocks.get(i), temp.get(i));
        }
        HashMap<Value, Value> oldValueToNew = new HashMap<>();
        for (int i = 0; i < callIns.getFunc().getParamNum(); i++) {
            oldValueToNew.put(callIns.getFunc().getFunctionType().getParam(i),
                callIns.getRealParams().get(i));
        }
        for (int i = 0; i < basicBlocks.size(); i++) {
            for (Instruction ins : basicBlocks.get(i).getInstructions()) {
                temp.get(i).addIns(ins.clone(oldBBToNew, oldValueToNew));
            }
        }
        return temp;
    }

    public FunctionType getFunctionType() {
        return functionType;
    }
}
