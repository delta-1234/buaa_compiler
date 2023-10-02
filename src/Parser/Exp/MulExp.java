package Parser.Exp;

import Lexer.LexType;
import Lexer.Lexer;

import java.util.ArrayList;

public class MulExp {
    private ArrayList<UnaryExp> unaryExps;
    private ArrayList<Integer> op;

    public MulExp() {
        unaryExps = new ArrayList<>();
        op = new ArrayList<>();
    }

    public void parse() {
        UnaryExp unaryExp = new UnaryExp();
        unaryExp.parse();
        unaryExps.add(unaryExp);
        while (true) {
            if (Lexer.getInstance().getLexType() == LexType.MULT) {
                op.add(1);
            } else if (Lexer.getInstance().getLexType() == LexType.MOD) {
                op.add(2);
            } else if (Lexer.getInstance().getLexType() == LexType.DIV) {
                op.add(3);
            } else {
                break;
            }
            Lexer.getInstance().next();
            UnaryExp tmp = new UnaryExp();
            tmp.parse();
            unaryExps.add(tmp);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < unaryExps.size() - 1; i++) {
            sb.append(unaryExps.get(i).toString());
            sb.append("<MulExp>\n");
            if (op.get(i) == 1) {
                sb.append("MULT *\n");
            } else if (op.get(i) == 2) {
                sb.append("MOD %\n");
            } else {
                sb.append("DIV /\n");
            }
        }
        sb.append(unaryExps.get(unaryExps.size() - 1));
        sb.append("<MulExp>\n");
        return sb.toString();
    }
}
