package parser.Exp;

import lexer.LexType;
import lexer.Lexer;

import java.util.ArrayList;

public class LOrExp {
    private ArrayList<LAndExp> lAndExps;

    public LOrExp() {
        lAndExps = new ArrayList<>();
    }

    public void parse() {
        LAndExp lAndExp = new LAndExp();
        lAndExp.parse();
        lAndExps.add(lAndExp);
        while (true) {
            if (Lexer.getInstance().getLexType() != LexType.OR) {
                break;
            }
            Lexer.getInstance().next();
            LAndExp tmp = new LAndExp();
            tmp.parse();
            lAndExps.add(tmp);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lAndExps.size() - 1; i++) {
            sb.append(lAndExps.get(i).toString());
            sb.append("<LOrExp>\n");
            sb.append("OR ||\n");
        }
        sb.append(lAndExps.get(lAndExps.size() - 1));
        sb.append("<LOrExp>\n");
        return sb.toString();
    }
}
