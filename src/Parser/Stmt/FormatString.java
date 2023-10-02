package Parser.Stmt;

import Lexer.*;

public class FormatString {
    private String string;
    public FormatString() {
        string = "";
    }

    public void parse() {
        string = Lexer.getInstance().getToken();
        Lexer.getInstance().next();
    }

    @Override
    public String toString() {
        return "STRCON " + string + "\n";
    }
}
