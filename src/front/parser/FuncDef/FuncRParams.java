package front.parser.FuncDef;

import front.lexer.LexType;
import front.lexer.Lexer;
import front.parser.Exp.AddExp;
import front.parser.Exp.Exp;

import java.util.ArrayList;
import java.util.HashSet;

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

    public int getParamNum() {
        return exps.size();
    }

    public ArrayList<Integer> getParamList() {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < exps.size(); i++) {
            tmp.add(exps.get(i).getDim());
        }
        return tmp;
    }

    public static HashSet<LexType> firstSet() {
        return Exp.firstSet();
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

    public AddExp getAddExp(int i) {
        return exps.get(i).getAddExp();
    }
}
