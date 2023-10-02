package Parser.FuncDef;

import Lexer.LexType;
import Lexer.Lexer;
import Parser.Exp.Exp;

import java.util.ArrayList;

public class FuncRParams {
    private ArrayList<Exp> exps;

    public FuncRParams() {
        exps = new ArrayList<>();
    }

    ////FuncRParams → Exp { ',' Exp }
    public void parse() {
        Exp exp = new Exp();
        exp.parse();
        exps.add(exp);
        while (true) {
            if (Lexer.getInstance().getLexType() != LexType.COMMA) {
                break;
            }
            Lexer.getInstance().next();
            Exp exp1 = new Exp();
            exp1.parse();
            exps.add(exp1);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(exps.get(0));
        for (int i = 1; i < exps.size(); i++) {
            sb.append("COMMA ,\n");
            sb.append(exps.get(i));
        }
        sb.append("<FuncRParams>\n");
        return sb.toString();
    }
}
