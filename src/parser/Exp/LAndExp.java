package parser.Exp;

import lexer.LexType;
import lexer.Lexer;

import java.util.ArrayList;

public class LAndExp {
    private ArrayList<EqExp> eqExps;

    public LAndExp() {
        eqExps = new ArrayList<>();
    }

    public void parse() {
        EqExp eqExp = new EqExp();
        eqExp.parse();
        eqExps.add(eqExp);
        while (true) {
            if (Lexer.getInstance().getLexType() != LexType.AND) {
                break;
            }
            Lexer.getInstance().next();
            EqExp tmp = new EqExp();
            tmp.parse();
            eqExps.add(tmp);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < eqExps.size() - 1; i++) {
            sb.append(eqExps.get(i).toString());
            sb.append("<LAndExp>\n");
            sb.append("AND &&\n");
        }
        sb.append(eqExps.get(eqExps.size() - 1));
        sb.append("<LAndExp>\n");
        return sb.toString();
    }
}
