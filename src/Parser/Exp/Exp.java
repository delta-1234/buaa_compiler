package Parser.Exp;

import Parser.Stmt.LVal;

public class Exp {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(addExp);
        sb.append("<Exp>\n");
        return sb.toString();
    }
}
