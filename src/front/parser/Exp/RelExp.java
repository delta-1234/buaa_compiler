package front.parser.Exp;

import front.lexer.LexType;
import front.lexer.Lexer;

import java.util.ArrayList;

public class RelExp {
    private ArrayList<AddExp> addExps;
    private ArrayList<Integer> op;

    public RelExp() {
        addExps = new ArrayList<>();
        op = new ArrayList<>();
    }

    //< > <= >=
    public void parse() {
        AddExp addExp = new AddExp();
        addExp.parse();
        addExps.add(addExp);
        while (true) {
            if (Lexer.getInstance().getLexType() == LexType.LSS) {
                op.add(1);
            } else if (Lexer.getInstance().getLexType() == LexType.GRE) {
                op.add(2);
            } else if (Lexer.getInstance().getLexType() == LexType.LEQ) {
                op.add(3);
            } else if (Lexer.getInstance().getLexType() == LexType.GEQ) {
                op.add(4);
            } else {
                break;
            }
            Lexer.getInstance().next();
            AddExp tmp = new AddExp();
            tmp.parse();
            addExps.add(tmp);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addExps.size() - 1; i++) {
            sb.append(addExps.get(i).toString());
            sb.append("<RelExp>\n");
            if (op.get(i) == 1) {
                sb.append("LSS <\n");
            } else if (op.get(i) == 2) {
                sb.append("GRE >\n");
            } else if (op.get(i) == 3) {
                sb.append("LEQ <=\n");
            } else {
                sb.append("GEQ >=\n");
            }
        }
        sb.append(addExps.get(addExps.size() - 1));
        sb.append("<RelExp>\n");
        return sb.toString();
    }

    public ArrayList<AddExp> getAddExps() {
        return addExps;
    }

    public ArrayList<Integer> getOp() {
        return op;
    }
}
