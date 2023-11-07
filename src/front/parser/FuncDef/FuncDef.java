package front.parser.FuncDef;

import errorHandle.Error;
import front.lexer.*;
import front.parser.Block;
import front.parser.Ident;
import front.symbolTable.Function;
import front.symbolTable.Symbol;
import front.symbolTable.SymbolTable;

import java.util.ArrayList;

public class FuncDef {
    private FuncType funcType;
    private Ident ident;
    private FuncFParams funcFParams;
    private Block block;
    public FuncDef() {
        funcType = null;
        ident = null;
        funcFParams = null;
        block = null;
    }

    //FuncDef → FuncType Ident '(' [FuncFParams] ')' Block
    public void parse() {
        funcType = new FuncType();
        funcType.parse();
        ident = new Ident();
        ident.parse();
        Symbol symbol = SymbolTable.search(ident.getName(), true, false);
        if (symbol != null) {
            Error.error('b', Lexer.getInstance().getLineNum());
            symbol = null;
        } else {
            symbol = new Function(SymbolTable.curId, ident.getName(), funcType.reType());
        }
        Function function = (Function) symbol;
        Lexer.getInstance().next();
        if (FuncFParams.firstSet().contains(Lexer.getInstance().getLexType())) {
            funcFParams = new FuncFParams();
            funcFParams.parse();
        }
        if (Lexer.getInstance().getLexType() != LexType.RPARENT) {
            Error.error('j', Lexer.getInstance().getLastNum());
        } else {
            Lexer.getInstance().next();
        }
        if (function != null) {
            if (funcFParams != null) {
                function.addParam(funcFParams.getParamList());
            } else {
                function.addParam(new ArrayList<>());
            }
            SymbolTable.getCurrentTable().directory.put(ident.getName(), function);
            SymbolTable.getNextTable().funcName = ident.getName();
        }
        block = new Block();
        block.parse();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(funcType);
        sb.append(ident);
        sb.append("LPARENT (\n");
        if (funcFParams != null) {
            sb.append(funcFParams);
        }
        sb.append("RPARENT )\n");
        sb.append(block);
        sb.append("<FuncDef>\n");
        return sb.toString();
    }

    public int getFuncType() {
        return funcType.reType();
    }

    public String getIdent() {
        return ident.getName();
    }

    public FuncFParams getFuncFParams() {
        return funcFParams;
    }

    public Block getBlock() {
        return block;
    }
}
