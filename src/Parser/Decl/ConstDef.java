package Parser.Decl;

import Lexer.*;
import Parser.Exp.ConstExp;
import Parser.Ident;
import Parser.Init.ConstInitVal;

import java.util.ArrayList;

public class ConstDef {
    private Ident ident;
    private ArrayList<ConstExp> constExps;
    private ConstInitVal constInitVal;

    public ConstDef() {
        ident = null;
        constExps = new ArrayList<>();
        constInitVal = null;
    }

    //ConstDef → Ident { '[' ConstExp ']' } '=' ConstInitVal
    public void parse() {
        ident = new Ident();
        ident.parse();
        while (true) {
            if (Lexer.getInstance().getLexType() != LexType.LBRACK) {
                break;
            }
            Lexer.getInstance().next();
            ConstExp constExp = new ConstExp();
            constExp.parse();
            constExps.add(constExp);
            if (Lexer.getInstance().getLexType() != LexType.RBRACK) {
                System.out.println("ConstDef error]");
                return; //error
            }
            Lexer.getInstance().next();
        }
        if (Lexer.getInstance().getLexType() != LexType.ASSIGN) {
            System.out.println("ConstDef error=");
            return; //error
        }
        Lexer.getInstance().next();
        constInitVal = new ConstInitVal();
        constInitVal.parse();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toString());
        for (int i = 0; i < constExps.size(); i++) {
            sb.append("LBRACK [\n");
            sb.append(constExps.get(i).toString());
            sb.append("RBRACK ]\n");
        }
        sb.append("ASSIGN =\n");
        sb.append(constInitVal.toString());
        sb.append("<ConstDef>\n");
        return sb.toString();
    }
}
