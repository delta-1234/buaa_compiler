package parser;

import lexer.*;

public class Ident {
    private String name;

    public Ident() {
        name = "";
    }

    public void parse() {
        name = Lexer.getInstance().getToken();
        Lexer.getInstance().next();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "IDENFR " + name + "\n";
    }
}
