package front.parser.Stmt;

import front.lexer.Lexer;

import java.util.ArrayList;

public class FormatString {
    private String string;
    private ArrayList<Integer> formatChar;

    public FormatString() {
        string = "";
        formatChar = new ArrayList<>();
    }

    public void parse() {
        string = Lexer.getInstance().getToken();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '%') {
                formatChar.add(i);
            }
        }
        Lexer.getInstance().next();
    }

    public int getNum() {
        return formatChar.size();
    }

    @Override
    public String toString() {
        return "STRCON " + string + "\n";
    }

    public String getString() {
        return string;
    }
}
