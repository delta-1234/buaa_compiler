package front.parser.Stmt;

import errorHandle.Error;
import front.lexer.LexType;
import front.lexer.Lexer;
import llvm.IRModule;
import llvm.type.ArrayType;
import llvm.type.IntegerType;
import llvm.type.PointerType;
import llvm.value.Value;
import llvm.value.constant.ConstInt;
import llvm.value.constant.GlobalArray;
import llvm.value.constant.GlobalVariable;
import llvm.value.instruction.AllocaIns;
import front.parser.Exp.AddExp;
import front.parser.Exp.Exp;
import front.parser.Ident;
import front.symbolTable.Symbol;
import front.symbolTable.SymbolTable;
import front.symbolTable.Variate;

import java.util.ArrayList;

public class LVal {
    private Ident ident;
    private ArrayList<Exp> exps;

    public LVal() {
        ident = null;
        exps = new ArrayList<>();
    }

    // LVal → Ident {'[' Exp ']'}
    public void parse() {
        ident = new Ident();
        ident.parse();
        if (SymbolTable.search(ident.getName(), false, true) == null) {
            Error.error('c', Lexer.getInstance().getLineNum());
        }
        while (true) {
            if (Lexer.getInstance().getLexType() != LexType.LBRACK) {
                break;
            }
            Lexer.getInstance().next();
            Exp exp = new Exp();
            exp.parse();
            exps.add(exp);
            if (Lexer.getInstance().getLexType() != LexType.RBRACK) {
                Error.error('k', Lexer.getInstance().getLastNum());
            } else {
                Lexer.getInstance().next();
            }
        }
    }

    public Ident getIdent() {
        return ident;
    }

    public int getDim() {
        Symbol symbol = SymbolTable.search(ident.getName(), false, true);
        if (symbol == null) {
            return 0;
        }
        return ((Variate) symbol).dim - exps.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident);
        for (int i = 0; i < exps.size(); i++) {
            sb.append("LBRACK [\n");
            sb.append(exps.get(i).toString());
            sb.append("RBRACK ]\n");
        }
        sb.append("<LVal>\n");
        return sb.toString();
    }

    public int getExpNum() {
        return exps.size();
    }

    public AddExp getAddExp(int i) {
        return exps.get(i).getAddExp();
    }

    public int cal(IRModule module) {
        Value value = module.getSymbolTable().search(ident.getName());
        if (value instanceof GlobalVariable globalVariable) {
            Value temp = globalVariable.getValue();
            if (temp instanceof ConstInt) {
                return ((ConstInt) temp).getValue();
            }
        } else if (value instanceof GlobalArray globalArray) {
            ArrayList<Integer> numIndex = new ArrayList<>();
            for (int i = 0; i < exps.size(); i++) {
                if (exps.get(i).getAddExp().isConst(module)) {
                    numIndex.add(exps.get(i).getAddExp().cal(module));
                } else {
                    return 0;
                }
            }
            ArrayType arrayType = (ArrayType) ((PointerType) globalArray.getType()).getPointType();
            if (numIndex.size() == arrayType.getDim()) {
                Value temp = globalArray.getValue(arrayType.getOffset(numIndex));
                if (temp instanceof ConstInt) {
                    return ((ConstInt) temp).getValue();
                } else {
                    return 0;
                }
            }
            return 0;
        } else if (value instanceof AllocaIns allocaIns) {
            if (allocaIns.getDefType() instanceof IntegerType) {
                Value temp = allocaIns.getValue(0);
                if (temp instanceof ConstInt) {
                    return ((ConstInt) temp).getValue();
                }
            } else if (allocaIns.getDefType() instanceof ArrayType) {
                ArrayList<Integer> numIndex = new ArrayList<>();
                for (int i = 0; i < exps.size(); i++) {
                    if (exps.get(i).getAddExp().isConst(module)) {
                        numIndex.add(exps.get(i).getAddExp().cal(module));
                    } else {
                        return 0;
                    }
                }
                ArrayType arrayType =
                    (ArrayType) ((PointerType) allocaIns.getType()).getPointType();
                if (numIndex.size() == arrayType.getDim()) {
                    Value temp = allocaIns.getValue(arrayType.getOffset(numIndex));
                    if (temp instanceof ConstInt) {
                        return ((ConstInt) temp).getValue();
                    } else {
                        return 0;
                    }
                }
                return 0;
            } else {
                return 0;
            }
        }
        return 0;
    }

    public boolean isConst(IRModule module) {
        Value value = module.getSymbolTable().search(ident.getName());
        if (value instanceof GlobalVariable globalVariable) {
            Value temp = globalVariable.getValue();
            return (temp instanceof ConstInt);
        } else if (value instanceof GlobalArray globalArray) {
            if (!globalArray.isConst()) {
                return false;
            }
            ArrayList<Integer> numIndex = new ArrayList<>();
            for (int i = 0; i < exps.size(); i++) {
                if (exps.get(i).getAddExp().isConst(module)) {
                    numIndex.add(exps.get(i).getAddExp().cal(module));
                } else {
                    return false;
                }
            }
            ArrayType arrayType = (ArrayType) ((PointerType) globalArray.getType()).getPointType();
            if (numIndex.size() == arrayType.getDim()) {
                Value temp = globalArray.getValue(arrayType.getOffset(numIndex));
                if (temp instanceof ConstInt) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } else if (value instanceof AllocaIns allocaIns) {
            if (!allocaIns.isConst()) {
                return false;
            }
            if (allocaIns.getDefType() instanceof IntegerType) {
                Value temp = allocaIns.getValue(0);
                return (temp instanceof ConstInt);
            } else if (allocaIns.getDefType() instanceof ArrayType){
                ArrayList<Integer> numIndex = new ArrayList<>();
                for (int i = 0; i < exps.size(); i++) {
                    if (exps.get(i).getAddExp().isConst(module)) {
                        numIndex.add(exps.get(i).getAddExp().cal(module));
                    } else {
                        return false;
                    }
                }
                ArrayType arrayType =
                    (ArrayType) ((PointerType) allocaIns.getType()).getPointType();
                if (numIndex.size() == arrayType.getDim()) {
                    Value temp = allocaIns.getValue(arrayType.getOffset(numIndex));
                    if (temp instanceof ConstInt) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return false;
            } else {
                return false;
            }
        }
        return false;
    }
}
