package front.parser;

import errorHandle.Error;
import front.lexer.*;
import front.symbolTable.Function;
import front.symbolTable.SymbolTable;

import java.util.ArrayList;

public class MainFuncDef {
    private Block block;
    public MainFuncDef() {
        block = null;
    }

    //MainFuncDef → 'int' 'main' '(' ')' Block
    public void parse() {
        Lexer.getInstance().next();
        Lexer.getInstance().next();
        Function function = new Function(SymbolTable.curId, "main", 1);
        Lexer.getInstance().next();
        if (Lexer.getInstance().getLexType() != LexType.RPARENT) {
            Error.error('j', Lexer.getInstance().getLastNum());
        } else {
            Lexer.getInstance().next();
        }
        function.addParam(new ArrayList<>());
        SymbolTable.getCurrentTable().directory.put("main", function);
        SymbolTable.getNextTable().funcName = "main";
        block = new Block();
        block.parse();
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("INTTK int\n");
        sb.append("MAINTK main\n");
        sb.append("LPARENT (\n");
        sb.append("RPARENT )\n");
        sb.append(block);
        sb.append("<MainFuncDef>\n");
        return sb.toString();
    }
}
