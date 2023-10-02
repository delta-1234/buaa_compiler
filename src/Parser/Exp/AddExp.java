package Parser.Exp;

import Lexer.LexType;
import Lexer.Lexer;

import java.util.ArrayList;

public class AddExp {
    private ArrayList<MulExp> mulExps;
    private ArrayList<Boolean> isNeg;

    public AddExp() {
        mulExps = new ArrayList<>();
        isNeg = new ArrayList<>();
    }


    public void parse() {
        MulExp mulExp = new MulExp();
        mulExp.parse();
        mulExps.add(mulExp);
        while (true) {
            if (Lexer.getInstance().getLexType() == LexType.PLUS) {
                isNeg.add(false);
            } else if (Lexer.getInstance().getLexType() == LexType.MINU) {
                isNeg.add(true);
            } else {
                break;
            }
            Lexer.getInstance().next();
            MulExp tmp = new MulExp();
            tmp.parse();
            mulExps.add(tmp);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mulExps.size() - 1; i++) {
            sb.append(mulExps.get(i));
            sb.append("<AddExp>\n");
            if (isNeg.get(i)) {
                sb.append("MINU -\n");
            } else {
                sb.append("PLUS +\n");
            }
        }
        sb.append(mulExps.get(mulExps.size() - 1));
        sb.append("<AddExp>\n");
        return sb.toString();
    }
}
