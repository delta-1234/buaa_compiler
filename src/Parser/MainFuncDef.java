package Parser;

import Lexer.*;

public class MainFuncDef {
    private Block block;
    public MainFuncDef() {
        block = null;
    }

    //MainFuncDef → 'int' 'main' '(' ')' Block
    public void parse() {
        if (Lexer.getInstance().getLexType() != LexType.INTTK) {
            System.out.println("MainFuncDef error");
            return; //error
        }
        Lexer.getInstance().next();
        if (Lexer.getInstance().getLexType() != LexType.MAINTK) {
            System.out.println("MainFuncDef error");
            return; //error
        }
        Lexer.getInstance().next();
        if (Lexer.getInstance().getLexType() != LexType.LPARENT) {
            System.out.println("MainFuncDef error(");
            return; //error
        }
        Lexer.getInstance().next();
        if (Lexer.getInstance().getLexType() != LexType.RPARENT) {
            System.out.println("MainFuncDef error)");
            return; //error
        }
        Lexer.getInstance().next();
        block = new Block();
        block.parse();
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
