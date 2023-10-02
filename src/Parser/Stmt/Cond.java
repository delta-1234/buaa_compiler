package Parser.Stmt;

import Parser.Exp.LOrExp;

public class Cond {
    private LOrExp lOrExp;
    public Cond() {
        lOrExp = null;
    }

    //Cond → LOrExp
    public void parse() {
        lOrExp = new LOrExp();
        lOrExp.parse();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(lOrExp);
        sb.append("<Cond>\n");
        return sb.toString();
    }
}
