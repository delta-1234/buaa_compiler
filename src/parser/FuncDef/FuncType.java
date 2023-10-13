package parser.FuncDef;

import lexer.LexType;
import lexer.Lexer;

public class FuncType {
    private String name;
    private LexType type;

    public FuncType() {
        name = "";
        type = LexType.WRONG;
    }

    //FuncType → 'void' | 'int'
    public void parse() {
        name = Lexer.getInstance().getToken();
        type = Lexer.getInstance().getLexType();
        Lexer.getInstance().next();
    }

    public int reType() {
        if (type == LexType.VOIDTK) {
            return 0;
        }
        return 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type + " " + name + "\n");
        sb.append("<FuncType>\n");
        return sb.toString();
    }
}
