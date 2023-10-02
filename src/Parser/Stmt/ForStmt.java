package Parser.Stmt;

import Lexer.*;
import Parser.Exp.Exp;

public class ForStmt {
    private LVal lVal;
    private Exp exp;
    public ForStmt() {
        lVal = null;
        exp = null;
    }

    //ForStmt → LVal '=' Exp
    public void parse() {
        lVal = new LVal();
        lVal.parse();
        if (Lexer.getInstance().getLexType() != LexType.ASSIGN) {
            System.out.println("ForStmt error=");
            return; //error
        }
        Lexer.getInstance().next();
        exp = new Exp();
        exp.parse();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(lVal);
        sb.append("ASSIGN =\n");
        sb.append(exp);
        sb.append("<ForStmt>\n");
        return sb.toString();
    }
}
