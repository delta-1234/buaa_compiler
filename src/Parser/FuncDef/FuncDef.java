package Parser.FuncDef;

import Lexer.*;
import Parser.Block;
import Parser.Ident;

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
        if (Lexer.getInstance().getLexType() != LexType.LPARENT) {
            System.out.println("FuncDef error(");
            return; //error
        }
        Lexer.getInstance().next();
        if (Lexer.getInstance().getLexType() != LexType.RPARENT) {
            funcFParams = new FuncFParams();
            funcFParams.parse();
        }
        if (Lexer.getInstance().getLexType() != LexType.RPARENT) {
            System.out.println("FuncDef error)");
            return;//error
        }
        Lexer.getInstance().next();
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
