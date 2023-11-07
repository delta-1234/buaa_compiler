package util;

import front.parser.Block;
import front.parser.CompUnit;
import front.parser.Decl.ConstDef;
import front.parser.Decl.Decl;
import front.parser.Decl.VarDef;
import front.parser.Stmt.LVal;
import front.parser.Stmt.Stmt;
import llvm.IRModule;
import llvm.type.ArrayType;
import llvm.type.IntegerType;
import llvm.type.PointerType;
import llvm.type.VoidType;
import llvm.value.BasicBlock;
import llvm.value.User;
import llvm.value.Value;
import llvm.value.constant.ConstInt;
import llvm.value.constant.GlobalArray;
import llvm.value.constant.GlobalVariable;
import llvm.value.constant.IRFunction;
import llvm.value.instruction.AllocaIns;
import llvm.value.instruction.BrIns;
import llvm.value.instruction.CallIns;
import llvm.value.instruction.GetIns;
import llvm.value.instruction.Instruction;
import llvm.value.instruction.LoadIns;
import llvm.value.instruction.Operation;
import llvm.value.instruction.RetIns;
import llvm.value.instruction.StoreIns;

import java.util.ArrayList;

public class Parser {
    public static CompUnit compUnit = new CompUnit();
    private static IRModule module = IRBuilder.module;

    public static void parseBlock(Block block, BasicBlock BB,
                                  BasicBlock breakBB, BasicBlock continueBB) {
        for (int i = 0; i < block.getBlockItems().size(); i++) {
            if (block.getBlockItems().get(i).getDecl() != null) {
                parseDecl(block.getBlockItems().get(i).getDecl(), BB.getFather().getLastBB());
            } else {
                parseStmt(block.getBlockItems().get(i).getStmt(), BB.getFather().getLastBB(),
                    breakBB, continueBB);
            }
        }
    }

    public static void parseDecl(Decl decl, BasicBlock BB) {
        if (decl.isConst()) {
            for (int i = 0; i < decl.getConstDecl().getConstDefs().size(); i++) {
                ConstDef temp = decl.getConstDecl().getConstDefs().get(i);
                if (temp.getDim() == 0) {
                    IRBuilder.buildVariable(temp, false, BB);
                } else {
                    IRBuilder.buildArray(temp, false, BB);
                }
            }
            return;
        }
        for (int i = 0; i < decl.getVarDecl().getVarDefs().size(); i++) {
            VarDef temp = decl.getVarDecl().getVarDefs().get(i);
            if (temp.getDim() == 0) {
                IRBuilder.buildVariable(temp, false, BB);
            } else {
                IRBuilder.buildArray(temp, false, BB);
            }
        }
    }

    public static void parseStmt(Stmt stmt, BasicBlock BB,
                                       BasicBlock breakBB, BasicBlock continueBB) {
        Instruction ins;
        Value lv;
        Value value;
        switch (stmt.getType()) {
            case 1: //LVal '=' Exp ';'
                Value exp = Calculator.calAddExp(stmt.getExps().get(0).getAddExp(), BB);
                lv = parseLVal(stmt.getlVal(), BB, exp);
                ins = new StoreIns("", null, BB, Operation.STORE, exp, (User) lv);
                BB.addIns(ins);
                break;
            case 2: //[Exp] ';'
                if (stmt.getExps().size() != 0 &&
                    stmt.getExps().get(0).getAddExp().hasFunc()) {
                    Calculator.calAddExp(stmt.getExps().get(0).getAddExp(), BB);
                }
                break;
            case 3: //Block
                module.getSymbolTable().addTable();
                parseBlock(stmt.getBlock(), BB, breakBB, continueBB);
                module.getSymbolTable().deleteTable();
                break;
            case 4: //'if' '(' Cond ')' Stmt [ 'else' Stmt ]
                parseIf(stmt, BB, breakBB, continueBB);
                break;
            case 5: //'for' '(' [ForStmt] ';' [Cond] ';' [forStmt] ')' Stmt
                parseFor(stmt, BB, breakBB, continueBB);
                break;
            case 6: //'break' ';'
                ins = new BrIns("", null, BB, Operation.BR, breakBB);
                BB.addIns(ins);
                break;
            case 7: //'continue' ';'
                ins = new BrIns("", null, BB, Operation.BR, continueBB);
                BB.addIns(ins);
                break;
            case 8: //'return' [Exp] ';'
                if (stmt.getExps().size() != 0) {
                    value = Calculator.calAddExp(stmt.getExps().get(0).getAddExp(), BB);
                    ins = new RetIns("", value.getType(), BB, Operation.RET, value);
                    BB.addIns(ins);
                } else {
                    ins = new RetIns("", new VoidType(), BB, Operation.RET);
                    BB.addIns(ins);
                }
                break;
            case 9: //LVal '=' 'getint''('')'';'
                value = module.getSymbolTable().search("getint");
                ins = new CallIns("", new IntegerType(32), BB, Operation.CALL,
                    (IRFunction) value, new ArrayList<>());
                BB.addIns(ins);
                lv = parseLVal(stmt.getlVal(), BB, ins);
                ins = new StoreIns("", null, BB, Operation.STORE, ins, (User) lv);
                BB.addIns(ins);
                break;
            case 10: //'printf''('FormatString{','Exp}')'';'
                parsePrint(stmt, BB);
                break;
        }
    }

    public static void parsePrint(Stmt stmt, BasicBlock BB) {
        String str = stmt.getFormatString().getString();
        Instruction ins;
        IRFunction putch = (IRFunction) module.getSymbolTable().search("putch");
        IRFunction putint = (IRFunction) module.getSymbolTable().search("putint");
        int index = 0;
        for (int i = 1; i < str.length() - 1; i++) {
            char c = str.charAt(i);
            if (c != '%' && c != '\\') {
                ArrayList<Value> params = new ArrayList<>();
                params.add(new ConstInt(Integer.toString(c), new IntegerType(32), c));
                ins = new CallIns("", new VoidType(), BB, Operation.CALL, putch, params);
                BB.addIns(ins);
            } else if (c == '\\') {
                c = '\n';
                ArrayList<Value> params = new ArrayList<>();
                params.add(new ConstInt(Integer.toString(c), new IntegerType(32), c));
                ins = new CallIns("", new VoidType(), BB, Operation.CALL, putch, params);
                BB.addIns(ins);
                i++;
            } else {
                i++;
                ArrayList<Value> params = new ArrayList<>();
                params.add(Calculator.calAddExp(stmt.getExps().get(index).getAddExp(), BB));
                ins = new CallIns("", new VoidType(), BB, Operation.CALL, putint, params);
                BB.addIns(ins);
                index++;
            }
        }
    }

    public static Value parseLVal(LVal lVal, BasicBlock BB, Value exp) {
        Value value = module.getSymbolTable().search(lVal.getIdent().getName());
        if (value instanceof GlobalVariable gv) {
            return value;
        } else if (value instanceof GlobalArray globalArray) {
            ArrayList<Value> index = new ArrayList<>();
            index.add(ConstInt.getZero());
            for (int i = 0; i < lVal.getExpNum(); i++) {
                index.add(Calculator.calAddExp(lVal.getAddExp(i), BB));
            }
            Instruction temp = new GetIns("", new PointerType(new IntegerType(32)), BB,
                Operation.GETELEMENTPTR, globalArray, index);
            BB.addIns(temp);
            return temp;
        } else if (value instanceof AllocaIns allocaIns) {
            if (allocaIns.getDefType() instanceof IntegerType) {
                return value;
            } else if (allocaIns.getDefType() instanceof ArrayType arrayType) {
                ArrayList<Value> index = new ArrayList<>();
                index.add(ConstInt.getZero());
                for (int i = 0; i < lVal.getExpNum(); i++) {
                    index.add(Calculator.calAddExp(lVal.getAddExp(i), BB));
                }
                Instruction temp = new GetIns("", new PointerType(new IntegerType(32)), BB,
                    Operation.GETELEMENTPTR, allocaIns, index);
                BB.addIns(temp);
                return temp;
            } else {
                ArrayList<Value> index = new ArrayList<>();
                for (int i = 0; i < lVal.getExpNum(); i++) {
                    index.add(Calculator.calAddExp(lVal.getAddExp(i), BB));
                }
                Instruction temp =
                    new LoadIns("", allocaIns.getDefType(), BB, Operation.LOAD, allocaIns);
                BB.addIns(temp);
                ArrayList<Value> t = new ArrayList<>();
                t.add(index.get(0));
                temp = new GetIns("", temp.getType(), BB, Operation.GETELEMENTPTR, temp, t);
                BB.addIns(temp);
                if (((PointerType) temp.getType()).getPointType() instanceof IntegerType) {
                    return temp;
                }
                index.set(0, ConstInt.getZero());
                temp = new GetIns("", new PointerType(new IntegerType(32)), BB,
                    Operation.GETELEMENTPTR, temp, index);
                BB.addIns(temp);
                return temp;
            }
        }
        return null;
    }

    public static void parseIf(Stmt stmt, BasicBlock BB, BasicBlock breakBB,
                               BasicBlock continueBB) {
        Instruction ins;
        Value value;
        IRFunction func = BB.getFather();
        BasicBlock newBB = new BasicBlock("", null, func);
        ins = new BrIns("", null, BB, Operation.BR, newBB);
        BB.addIns(ins);
        func.addBB(newBB);
        BasicBlock trueIf = new BasicBlock("", null, func);
        BasicBlock falseIf = new BasicBlock("", null, func);
        BasicBlock normal = new BasicBlock("", null, func);
        BasicBlock temp;
        if (stmt.getStmts().size() > 1) {
            value = Calculator.calOr(stmt.getCond().getlOrExp(), newBB, trueIf, falseIf);
            temp = falseIf;
        } else {
            value = Calculator.calOr(stmt.getCond().getlOrExp(), newBB, trueIf, normal);
            temp = normal;
        }
        if (value instanceof ConstInt) {
            if (((ConstInt) value).getValue() == 1) {
                ins = new BrIns("", null, func.getLastBB(), Operation.BR,
                    trueIf);
            } else {
                ins = new BrIns("", null, func.getLastBB(), Operation.BR,
                    temp);
            }
        } else {
            ins = new BrIns("", null, func.getLastBB(), Operation.BR,
                value, trueIf, temp);
        }
        func.getLastBB().addIns(ins);
        func.addBB(trueIf);
        parseStmt(stmt.getStmts().get(0), trueIf, breakBB, continueBB);
        ins = new BrIns("", null, func.getLastBB(), Operation.BR, normal);
        func.getLastBB().addIns(ins);
        if (stmt.getStmts().size() > 1) {
            func.addBB(falseIf);
            parseStmt(stmt.getStmts().get(1), falseIf, breakBB, continueBB);
            ins = new BrIns("", null, func.getLastBB(), Operation.BR, normal);
            func.getLastBB().addIns(ins);
        }
        func.addBB(normal);
    }

    public static void parseFor(Stmt stmt, BasicBlock BB, BasicBlock breakBB,
                                BasicBlock continueBB) {
        IRFunction func = BB.getFather();
        if (stmt.getForStmt1() != null) {
            parseStmt(stmt.getForStmt1().getStmt(), BB, breakBB, continueBB);
        }
        BasicBlock condBB = new BasicBlock("", null, func);
        BasicBlock trueBB = new BasicBlock("", null, func);
        BasicBlock falseBB = new BasicBlock("", null, func);
        Instruction ins = new BrIns("", null, func.getLastBB(), Operation.BR, condBB);
        func.getLastBB().addIns(ins);
        func.addBB(condBB);
        if (stmt.getCond() != null) {
            Value value = Calculator.calOr(stmt.getCond().getlOrExp(), condBB, trueBB, falseBB);
            if (value instanceof ConstInt) {
                if (((ConstInt) value).getValue() == 1) {
                    ins = new BrIns("", null, func.getLastBB(), Operation.BR,
                        trueBB);
                } else {
                    ins = new BrIns("", null, func.getLastBB(), Operation.BR,
                        falseBB);
                }
            } else {
                ins = new BrIns("", null, func.getLastBB(), Operation.BR,
                    value, trueBB, falseBB);
            }
        } else {
            ins = new BrIns("", null, func.getLastBB(), Operation.BR,
                trueBB);
        }
        func.getLastBB().addIns(ins);
        func.addBB(trueBB);
        BasicBlock format2BB = new BasicBlock("", null, func);
        parseStmt(stmt.getStmts().get(0), trueBB, falseBB, format2BB);
        ins = new BrIns("", null, func.getLastBB(), Operation.BR, format2BB);
        func.getLastBB().addIns(ins);
        func.addBB(format2BB);
        if (stmt.getForStmt2() != null) {
            parseStmt(stmt.getForStmt2().getStmt(), format2BB, breakBB, continueBB);
        }
        ins = new BrIns("", null, func.getLastBB(), Operation.BR, condBB);
        func.getLastBB().addIns(ins);
        func.addBB(falseBB);
    }
}
