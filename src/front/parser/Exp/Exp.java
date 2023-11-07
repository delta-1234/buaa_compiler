package front.parser.Exp;

import front.lexer.LexType;
import front.parser.Stmt.LVal;

import java.util.HashSet;

public class Exp {
    private static HashSet<LexType> firstSet = new HashSet<>();
    private AddExp addExp;
    public Exp() {
        addExp = null;
    }

    //Exp → AddExp
    public void parse() {
        addExp = new AddExp();
        addExp.parse();
    }
    public void parse(LVal lVal) {
        addExp = new AddExp();
        addExp.parse(lVal);
    }

    public int getDim() {
        return addExp.getDim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(addExp);
        sb.append("<Exp>\n");
        return sb.toString();
    }

    public static HashSet<LexType> firstSet() {
        if (firstSet.size() == 0) {
            firstSet.add(LexType.IDENFR);
            firstSet.add(LexType.INTCON);
            firstSet.add(LexType.LPARENT);
            firstSet.add(LexType.PLUS);
            firstSet.add(LexType.MINU);
            firstSet.add(LexType.NOT);
        }
        return firstSet;
    }

    public AddExp getAddExp() {
        return addExp;
    }
}
