package Parser.Decl;

import Lexer.*;
import Parser.Ident;
import Parser.Init.InitVal;
import Parser.Exp.ConstExp;

import java.util.ArrayList;

public class VarDef {
    private Ident ident;
    private ArrayList<ConstExp> constExps;
    private InitVal initVal;
    public VarDef() {
        ident = null;
        constExps = new ArrayList<>();
        initVal = null;
    }

    //VarDef → Ident { '[' ConstExp ']' } [ '=' InitVal ]
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
                System.out.println("VarDef error");
                return; //error
            }
            Lexer.getInstance().next();
        }
        if (Lexer.getInstance().getLexType() != LexType.ASSIGN) {
            return;
        }
        Lexer.getInstance().next();
        initVal = new InitVal();
        initVal.parse();
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
        if (initVal != null) {
            sb.append("ASSIGN =\n");
            sb.append(initVal.toString());
        }
        sb.append("<VarDef>\n");
        return sb.toString();
    }
}
