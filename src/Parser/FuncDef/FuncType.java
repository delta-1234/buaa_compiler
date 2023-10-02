package Parser.FuncDef;

import Lexer.*;

public class FuncType {
    private String name;
    private LexType type;
    public FuncType() {
        name = "";
        type = LexType.WRONG;
    }

    //FuncType → 'void' | 'int'
    public void parse() {
        if (Lexer.getInstance().getLexType() == LexType.VOIDTK ||
            Lexer.getInstance().getLexType() == LexType.INTTK) {
            name = Lexer.getInstance().getToken();
            type = Lexer.getInstance().getLexType();
            Lexer.getInstance().next();
        } else {
            System.out.println("FuncType error");
            return;//error
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type + " " + name + "\n");
        sb.append("<FuncType>\n");
        return sb.toString();
    }
}
