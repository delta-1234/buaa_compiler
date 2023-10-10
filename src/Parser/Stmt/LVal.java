package Parser.Stmt;

import Lexer.*;
import Parser.Exp.Exp;
import Parser.Ident;

import java.util.ArrayList;

public class LVal {
    private Ident ident;
    private ArrayList<Exp> exps;
    public LVal() {
        ident = null;
        exps = new ArrayList<>();
    }

    // LVal → Ident {'[' Exp ']'}
    public void parse() {
        ident = new Ident();
        ident.parse();
        while (true) {
            if (Lexer.getInstance().getLexType() != LexType.LBRACK) {
                break;
            }
            Lexer.getInstance().next();
            Exp exp = new Exp();
            exp.parse();
            exps.add(exp);
            if (Lexer.getInstance().getLexType() != LexType.RBRACK) {
                System.out.println("LVal error]");
                return; //error
            }
            Lexer.getInstance().next();
        }
    }

    public Ident getIdent() {
        return ident;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident);
        for (int i = 0; i < exps.size(); i++) {
            sb.append("LBRACK [\n");
            sb.append(exps.get(i).toString());
            sb.append("RBRACK ]\n");
        }
        sb.append("<LVal>\n");
        return sb.toString();
    }
}
