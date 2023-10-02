package Parser.Exp;

import Lexer.Lexer;

public class Number {
    private int num;

    //Number → IntConst
    public void parse() {
        num = Integer.parseInt(Lexer.getInstance().getToken());
        Lexer.getInstance().next();
    }

    @Override
    public String toString() {
        return "INTCON " + num + "\n" + "<Number>\n";
    }
}
