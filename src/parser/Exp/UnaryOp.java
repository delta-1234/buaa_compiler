package parser.Exp;

import lexer.*;

public class UnaryOp {
    private LexType type;
    private String name;
    public UnaryOp() {
        type = LexType.WRONG;
        name = "";
    }
    public void parse() {
        name = Lexer.getInstance().getToken();
        type = Lexer.getInstance().getLexType();
        Lexer.getInstance().next();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type + " " + name + "\n");
        sb.append("<UnaryOp>\n");
        return sb.toString();
    }
}
