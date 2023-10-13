package parser.Stmt;

import lexer.*;
import parser.Exp.Exp;

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
