package Parser.Exp;

import Lexer.*;
import Parser.Stmt.LVal;

public class PrimaryExp {
    private Exp exp;
    private LVal lVal;
    private Number number;
    public PrimaryExp() {
        exp = null;
        lVal = null;
        number = null;
    }

    //PrimaryExp → '(' Exp ')' | LVal | Number
    public void parse() {
        if (Lexer.getInstance().getLexType() == LexType.LPARENT) {
            Lexer.getInstance().next();
            exp = new Exp();
            exp.parse();
            if (Lexer.getInstance().getLexType() != LexType.RPARENT) {
                System.out.println("PrimaryExp error)");
                return;//error
            }
            Lexer.getInstance().next();
        } else if (Lexer.getInstance().getLexType() == LexType.IDENFR) {
            lVal = new LVal();
            lVal.parse();
        } else {
            number = new Number();
            number.parse();
        }
    }

    public void parse(LVal lVal) {
        this.lVal = lVal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (exp != null) {
            sb.append("LPARENT (\n");
            sb.append(exp);
            sb.append("RPARENT )\n");
        } else if (lVal != null) {
            sb.append(lVal);
        } else {
            sb.append(number);
        }
        sb.append("<PrimaryExp>\n");
        return sb.toString();
    }
}
