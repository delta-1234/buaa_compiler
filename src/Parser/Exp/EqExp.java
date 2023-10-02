package Parser.Exp;

import Lexer.LexType;
import Lexer.Lexer;

import java.util.ArrayList;

public class EqExp {
    private ArrayList<RelExp> relExps;
    private ArrayList<Boolean> isEqual;

    public EqExp() {
        relExps = new ArrayList<>();
        isEqual = new ArrayList<>();
    }

    public void parse() {
        RelExp relExp = new RelExp();
        relExp.parse();
        relExps.add(relExp);
        while (true) {
            if (Lexer.getInstance().getLexType() == LexType.EQL) {
                isEqual.add(true);
            } else if (Lexer.getInstance().getLexType() == LexType.NEQ) {
                isEqual.add(false);
            } else {
                break;
            }
            Lexer.getInstance().next();
            RelExp tmp = new RelExp();
            tmp.parse();
            relExps.add(tmp);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < relExps.size() - 1; i++) {
            sb.append(relExps.get(i).toString());
            sb.append("<EqExp>\n");
            if (isEqual.get(i)) {
                sb.append("EQL ==\n");
            } else {
                sb.append("NEQ !=\n");
            }
        }
        sb.append(relExps.get(relExps.size() - 1));
        sb.append("<EqExp>\n");
        return sb.toString();
    }
}
