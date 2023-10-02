package Parser.FuncDef;

import Lexer.*;
import Parser.Exp.ConstExp;
import Parser.Ident;

import java.util.ArrayList;

public class FuncFParam {
    private Ident ident;
    private boolean isArray;
    private ArrayList<ConstExp> constExps;
    public FuncFParam() {
        ident = null;
        isArray = false;
        constExps = new ArrayList<>();
    }

    //FuncFParam → BType Ident ['[' ']' { '[' ConstExp ']' }]
    public void parse() {
        if (Lexer.getInstance().getLexType() != LexType.INTTK) {
            System.out.println("FuncFParam error");
            return; //error
        }
        Lexer.getInstance().next();
        ident = new Ident();
        ident.parse();
        if (Lexer.getInstance().getLexType() != LexType.LBRACK) {
            return;
        }
        isArray = true;
        Lexer.getInstance().next();
        if (Lexer.getInstance().getLexType() != LexType.RBRACK) {
            System.out.println("FuncFParam error]");
            return; //error
        }
        Lexer.getInstance().next();
        while (true) {
            if (Lexer.getInstance().getLexType() != LexType.LBRACK) {
                break;
            }
            Lexer.getInstance().next();
            ConstExp constExp = new ConstExp();
            constExp.parse();
            constExps.add(constExp);
            if (Lexer.getInstance().getLexType() != LexType.RBRACK) {
                System.out.println("FuncFParam error]");
                return; //error
            }
            Lexer.getInstance().next();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("INTTK int\n");
        sb.append(ident);
        if (isArray) {
            sb.append("LBRACK [\n");
            sb.append("RBRACK ]\n");
            for (int i = 0; i < constExps.size(); i++) {
                sb.append("LBRACK [\n");
                sb.append(constExps.get(i).toString());
                sb.append("RBRACK ]\n");
            }
        }
        sb.append("<FuncFParam>\n");
        return sb.toString();
    }
}
