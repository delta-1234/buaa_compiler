package front.parser.Exp;

import front.lexer.*;

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

    public int getOp() {
        if (type == LexType.PLUS) {
            return 1;
        } else if (type == LexType.MINU) {
            return 2;
        }
        return 3;
    }
}
