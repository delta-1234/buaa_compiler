package Parser.Exp;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(addExp);
        sb.append("<Exp>\n");
        return sb.toString();
    }
}
