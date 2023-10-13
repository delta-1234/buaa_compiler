package parser.Exp;

public class ConstExp {
    private AddExp addExp;
    public ConstExp() {
        addExp = null;
    }

    //ConstExp → AddExp
    public void parse() {
        addExp = new AddExp();
        addExp.parse();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(addExp);
        sb.append("<ConstExp>\n");
        return sb.toString();
    }
}
