package front.parser.Exp;

import front.lexer.Lexer;

public class Number {
    private int num;

    //Number → IntConst
    public void parse() {
        num = Integer.parseInt(Lexer.getInstance().getToken());
        Lexer.getInstance().next();
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "INTCON " + num + "\n" + "<Number>\n";
    }
}
