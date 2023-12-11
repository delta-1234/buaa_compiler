package util;

import llvm.IRModule;
import llvm.type.ArrayType;
import llvm.type.FunctionType;
import llvm.type.IntegerType;
import llvm.type.PointerType;
import llvm.value.Argument;
import llvm.value.BasicBlock;
import llvm.value.Value;
import llvm.value.constant.ConstInt;
import llvm.value.constant.GlobalArray;
import llvm.value.constant.GlobalVariable;
import llvm.value.constant.IRFunction;
import llvm.value.instruction.AllocaIns;
import llvm.value.instruction.GetIns;
import llvm.value.instruction.Instruction;
import llvm.value.instruction.Operation;
import llvm.value.instruction.StoreIns;
import front.parser.CompUnit;
import front.parser.Decl.ConstDef;
import front.parser.Decl.Decl;
import front.parser.Decl.VarDef;
import front.parser.Exp.ConstExp;
import front.parser.FuncDef.FuncDef;
import front.parser.FuncDef.FuncFParams;
import front.parser.Init.ConstInitVal;
import front.parser.Init.InitVal;
import front.parser.MainFuncDef;

import java.util.ArrayList;

public class IRBuilder {
    private static CompUnit compUnit = Parser.compUnit;
    public static IRModule module = new IRModule();
    public static boolean better = true;

    public static void buildIR() {
        buildModule();
    }

    public static void buildModule() {
        module.getSymbolTable().addTable();
        FunctionType functionType = new FunctionType(1, new ArrayList<>());
        IRFunction func = new IRFunction("getint", functionType);
        module.getSymbolTable().addSymbol(func);
        Argument argument = new Argument("", new IntegerType(32));
        ArrayList<Argument> arguments = new ArrayList<>();
        arguments.add(argument);
        functionType = new FunctionType(0, arguments);
        func = new IRFunction("putint", functionType);
        module.getSymbolTable().addSymbol(func);
        func = new IRFunction("putch", functionType);
        module.getSymbolTable().addSymbol(func);
        for (int i = 0; i < compUnit.getDecls().size(); i++) {
            buildGlobalVar(compUnit.getDecls().get(i));
        }
        module.setUnCertain();
        for (int i = 0; i < compUnit.getFuncDefs().size(); i++) {
            buildFunc(compUnit.getFuncDefs().get(i));
        }
        buildFunc(compUnit.getMainFuncDef());
        module.getSymbolTable().deleteTable();
        module.setCertain();
        if (better) {
            for (IRFunction function : module.getFunctions()) {
                function.deleteDead();
                function.buildCFG();
                function.insertPhi();
                function.deletePhi();
                function.calSimplify();
                function.LVN();
                function.deleteDead();
                function.deleteDead();
            }
        }
    }

    public static void buildGlobalVar(Decl decl) {
        if (decl.isConst()) {
            for (int i = 0; i < decl.getConstDecl().getConstDefs().size(); i++) {
                ConstDef temp = decl.getConstDecl().getConstDefs().get(i);
                if (temp.getDim() == 0) {
                    buildVariable(temp, true, null);
                } else {
                    buildArray(temp, true, null);
                }
            }
            return;
        }
        for (int i = 0; i < decl.getVarDecl().getVarDefs().size(); i++) {
            VarDef temp = decl.getVarDecl().getVarDefs().get(i);
            if (temp.getDim() == 0) {
                buildVariable(temp, true, null);
            } else {
                buildArray(temp, true, null);
            }
        }
    }

    public static void buildVariable(ConstDef constDef, boolean isGlobal, BasicBlock BB) {
        IntegerType integerType = new IntegerType(32);
        ConstInt constInt =
            (ConstInt) Calculator.calAddExp(constDef.getConstInitVal().getAddExp(), null);
        if (isGlobal) {
            GlobalVariable gv =
                new GlobalVariable(constDef.getIdent(), new PointerType(integerType), constInt,
                    true);
            //module.addGlobalVar(gv);
            module.getSymbolTable().addSymbol(gv);
        } else {
            AllocaIns allocaIns =
                new AllocaIns(constDef.getIdent(), new PointerType(integerType), BB,
                    Operation.ALLOCA, true);
            //BB.addIns(allocaIns);
            module.getSymbolTable().addSymbol(allocaIns);
            //StoreIns storeIns = new StoreIns("", null, BB, Operation.STORE, constInt, allocaIns);
            allocaIns.putValue(0, constInt);
            //BB.addIns(storeIns);
        }
    }

    //重载
    public static void buildVariable(VarDef varDef, boolean isGlobal, BasicBlock BB) {
        IntegerType integerType = new IntegerType(32);
        if (isGlobal) {
            int ans = 0;
            if (varDef.getInitVal() != null) {
                ans = ((ConstInt) Calculator.calAddExp(varDef.getInitVal().getAddExp(),
                    null)).getValue();
            }
            ConstInt constInt = new ConstInt(Integer.toString(ans), new IntegerType(32), ans);
            GlobalVariable gv = new GlobalVariable(varDef.getIdent(), new PointerType(integerType),
                constInt, false);
            module.addGlobalVar(gv);
            module.getSymbolTable().addSymbol(gv);
            return;
        }
        Instruction temp =
            new AllocaIns(varDef.getIdent(), new PointerType(integerType), BB, Operation.ALLOCA, false);
        BB.addIns(temp);
        module.getSymbolTable().addSymbol(temp);
        if (varDef.getInitVal() == null) {
            return;
        }
        Value value = Calculator.calAddExp(varDef.getInitVal().getAddExp(), BB);
        ((AllocaIns) temp).putValue(0, value);
        temp = new StoreIns("", null, BB, Operation.STORE, value, temp);
        BB.addIns(temp);
    }

    public static void buildArray(ConstDef constDef, boolean isGlobal, BasicBlock BB) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < constDef.getDim(); i++) {
            temp.add(((ConstInt) Calculator.calAddExp(constDef.getAddExp(i), BB)).getValue());
        }
        ArrayType arrayType = new ArrayType(constDef.getDim(), temp);
        if (isGlobal) {
            GlobalArray ga = new GlobalArray(constDef.getIdent(), new PointerType(arrayType), true);
            arrayInit(ga, constDef.getConstInitVal(), new ArrayList<Integer>(), null);
            module.addGlobalArr(ga);
            module.getSymbolTable().addSymbol(ga);
            return;
        }
        Instruction ins =
            new AllocaIns(constDef.getIdent(), new PointerType(arrayType), BB, Operation.ALLOCA, true);
        BB.addIns(ins);
        module.getSymbolTable().addSymbol(ins);
        arrayInit(ins, constDef.getConstInitVal(), new ArrayList<Integer>(), BB);
    }

    //重载
    public static void buildArray(VarDef varDef, boolean isGlobal, BasicBlock BB) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < varDef.getDim(); i++) {
            temp.add(((ConstInt) Calculator.calAddExp(varDef.getAddExp(i), null)).getValue());
        }
        ArrayType arrayType = new ArrayType(varDef.getDim(), temp);
        if (isGlobal) {
            GlobalArray ga = new GlobalArray(varDef.getIdent(), new PointerType(arrayType), false);
            if (varDef.getInitVal() != null) {
                arrayInit(ga, varDef.getInitVal(), new ArrayList<Integer>(), BB);
            }
            module.addGlobalArr(ga);
            module.getSymbolTable().addSymbol(ga);
            return;
        }
        Instruction ins =
            new AllocaIns(varDef.getIdent(), new PointerType(arrayType), BB, Operation.ALLOCA, false);
        BB.addIns(ins);
        module.getSymbolTable().addSymbol(ins);
        if (varDef.getInitVal() != null) {
            arrayInit(ins, varDef.getInitVal(), new ArrayList<Integer>(), BB);
        }
    }

    private static void arrayInit(Value value, ConstInitVal constInitVal, ArrayList<Integer> index,
                                  BasicBlock BB) {
        if (value instanceof GlobalArray ga) {
            if (constInitVal.getInitNum() == 0) {
                ArrayType temp = (ArrayType) ((PointerType) ga.getType()).getPointType();
                int loc = temp.getOffset(index);
                ConstInt constInt = (ConstInt) Calculator.calAddExp(constInitVal.getAddExp(), null);
                ga.putValue(loc, constInt);
                return;
            }
            for (int i = 0; i < constInitVal.getInitNum(); i++) {
                index.add(i);
                arrayInit(ga, constInitVal.getConstInitVal(i), index, BB);
                index.remove(index.size() - 1);
            }
        } else if (value instanceof AllocaIns allocaIns) {
            if (constInitVal.getInitNum() == 0) {
                ArrayType temp = (ArrayType) ((PointerType) allocaIns.getType()).getPointType();
                int loc = temp.getOffset(index);
                ConstInt constInt = (ConstInt) Calculator.calAddExp(constInitVal.getAddExp(), BB);
                allocaIns.putValue(loc, constInt);
                ArrayList<Value> t = new ArrayList<>();
                t.add(ConstInt.getZero());
                for (int i = 0; i < index.size(); i++) {
                    t.add(new ConstInt(Integer.toString(index.get(i)), new IntegerType(32),
                        index.get(i)));
                }
                Instruction ins = new GetIns("", new PointerType(new IntegerType(32)), BB,
                    Operation.GETELEMENTPTR, allocaIns, t);
                BB.addIns(ins);
                ins = new StoreIns("", null, BB, Operation.STORE, constInt, ins);
                BB.addIns(ins);
                return;
            }
            for (int i = 0; i < constInitVal.getInitNum(); i++) {
                index.add(i);
                arrayInit(allocaIns, constInitVal.getConstInitVal(i), index, BB);
                index.remove(index.size() - 1);
            }
        }
    }

    private static void arrayInit(Value value, InitVal initVal, ArrayList<Integer> index,
                                  BasicBlock BB) {
        if (value instanceof GlobalArray ga) {
            if (initVal.getInitNum() == 0) {
                ArrayType temp = (ArrayType) ((PointerType) ga.getType()).getPointType();
                int loc = temp.getOffset(index);
                ConstInt constInt = (ConstInt) Calculator.calAddExp(initVal.getAddExp(), null);
                ga.putValue(loc, constInt);
                return;
            }
            for (int i = 0; i < initVal.getInitNum(); i++) {
                index.add(i);
                arrayInit(ga, initVal.getInitVal(i), index, null);
                index.remove(index.size() - 1);
            }
        } else if (value instanceof AllocaIns allocaIns) {
            if (initVal.getInitNum() == 0) {
                ArrayType temp = (ArrayType) ((PointerType) allocaIns.getType()).getPointType();
                int loc = temp.getOffset(index);
                Value v = Calculator.calAddExp(initVal.getAddExp(), BB);
                allocaIns.putValue(loc, v);
                ArrayList<Value> t = new ArrayList<>();
                t.add(ConstInt.getZero());
                for (int i = 0; i < index.size(); i++) {
                    t.add(new ConstInt(Integer.toString(index.get(i)), new IntegerType(32),
                        index.get(i)));
                }
                Instruction ins = new GetIns("", new PointerType(new IntegerType(32)), BB,
                    Operation.GETELEMENTPTR, allocaIns, t);
                BB.addIns(ins);
                ins = new StoreIns("", null, BB, Operation.STORE, v, ins);
                BB.addIns(ins);
                return;
            }
            for (int i = 0; i < initVal.getInitNum(); i++) {
                index.add(i);
                arrayInit(allocaIns, initVal.getInitVal(i), index, BB);
                index.remove(index.size() - 1);
            }
        }
    }

    public static void buildFunc(FuncDef funcDef) {
        ArrayList<Argument> params = new ArrayList<>();
        FuncFParams funcFParams = funcDef.getFuncFParams();
        if (funcFParams != null) {
            for (int i = 0; i < funcFParams.getParamNum(); i++) {
                Argument argument;
                if (!funcFParams.getParam(i).isArray()) {
                    argument = new Argument(funcFParams.getParam(i).getIdent(),
                        new IntegerType(32));
                    params.add(argument);
                    continue;
                }
                if (funcFParams.getParam(i).getConstExps().size() == 0) {
                    argument = new Argument(funcFParams.getParam(i).getIdent(),
                        new PointerType(new IntegerType(32)));
                    params.add(argument);
                    continue;
                }
                ArrayList<Integer> dimSize = new ArrayList<>();
                ArrayList<ConstExp> constExps = funcFParams.getParam(i).getConstExps();
                for (int j = 0; j < constExps.size(); j++) {
                    ConstInt temp =
                        (ConstInt) Calculator.calAddExp(constExps.get(j).getAddExp(), null);
                    dimSize.add(temp.getValue());
                }
                ArrayType arrayType = new ArrayType(dimSize.size(), dimSize);
                argument = new Argument(funcFParams.getParam(i).getIdent(),
                    new PointerType(arrayType));
                params.add(argument);
            }
        }
        FunctionType funcType = new FunctionType(funcDef.getFuncType(), params);
        IRFunction function = new IRFunction(funcDef.getIdent(), funcType);
        module.addFunction(function);
        module.getSymbolTable().addSymbol(function);
        module.getSymbolTable().addTable();
        BasicBlock BB = new BasicBlock("", null, function);
        function.addBB(BB);
        for (int i = funcType.getParamNum() - 1; i >= 0; i--) {
            funcType.getParam(i).setFunction(function);
            Instruction temp = new AllocaIns(funcType.getParam(i).getName(),
                new PointerType(funcType.getParam(i).getType()), BB, Operation.ALLOCA, false);
            BB.addIns(temp);
            module.getSymbolTable().addSymbol(temp);
            temp = new StoreIns("", null, BB, Operation.STORE, funcType.getParam(i), temp);
            BB.addIns(temp);
        }
        Parser.parseBlock(funcDef.getBlock(), BB, null, null);
        module.getSymbolTable().deleteTable();
    }

    public static void buildFunc(MainFuncDef mainFuncDef) {
        ArrayList<Argument> params = new ArrayList<>();
        FunctionType funcType = new FunctionType(1, params);
        IRFunction function = new IRFunction("main", funcType);
        module.addFunction(function);
        module.getSymbolTable().addSymbol(function);
        module.getSymbolTable().addTable();
        BasicBlock BB = new BasicBlock("", null, function);
        function.addBB(BB);
        Parser.parseBlock(mainFuncDef.getBlock(), BB, null, null);
        module.getSymbolTable().deleteTable();
    }

}
