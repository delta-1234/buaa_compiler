package parser.FuncDef;

import errorHandle.Error;
import lexer.*;
import parser.Block;
import parser.Ident;
import symbolTable.Function;
import symbolTable.Symbol;
import symbolTable.SymbolTable;

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
        Function function = (Function) SymbolTable.search(ident.getName(), true, false);
        if (function != null) {
            Error.error('b', Lexer.getInstance().getLineNum());
            function = null;
        } else {
            function = new Function(SymbolTable.curId, ident.getName(), funcType.reType());
        }
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
}
