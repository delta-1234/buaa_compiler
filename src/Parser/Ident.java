package Parser;

import Lexer.*;

public class Ident {
    private String name;

    public Ident() {
        name = "";
    }

    public void parse() {
        if (Lexer.getInstance().getLexType() != LexType.IDENFR) {
            System.out.println("Ident error" + Lexer.getInstance().getToken());
            return;
        }
        name = Lexer.getInstance().getToken();
        Lexer.getInstance().next();
    }

    @Override
    public String toString() {
        return "IDENFR " + name + "\n";
    }
}
