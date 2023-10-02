package Parser.Init;

import Lexer.*;
import Parser.Exp.Exp;

import java.util.ArrayList;

public class InitVal {

    private Exp exp;
    private ArrayList<InitVal> initVals;
    public InitVal() {
        exp = null;
        initVals = new ArrayList<>();
    }

    //InitVal → Exp | '{' [ InitVal { ',' InitVal } ] '}'
    public void parse() {
        if (Lexer.getInstance().getLexType() != LexType.LBRACE) {
            exp = new Exp();
            exp.parse();
        } else {
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() == LexType.RBRACE) {
                Lexer.getInstance().next();
                return;
            } else {
                InitVal initVal = new InitVal();
                initVal.parse();
                initVals.add(initVal);
                while (true) {
                    if (Lexer.getInstance().getLexType() != LexType.COMMA) {
                        break;
                    }
                    Lexer.getInstance().next();
                    InitVal tmp = new InitVal();
                    tmp.parse();
                    initVals.add(tmp);
                }
                if (Lexer.getInstance().getLexType() != LexType.RBRACE) {
                    System.out.println("InitVal error}");
                    return; //error
                }
                Lexer.getInstance().next();
            }

        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (exp != null) {
            sb.append(exp.toString());
            sb.append("<InitVal>\n");
            return sb.toString();
        }
        sb.append("LBRACE {\n");
        if (initVals.size() != 0) {
            sb.append(initVals.get(0).toString());
        }
        for (int i = 1; i < initVals.size(); i++) {
            sb.append("COMMA ,\n");
            sb.append(initVals.get(i).toString());
        }
        sb.append("RBRACE }\n");
        sb.append("<InitVal>\n");
        return sb.toString();
    }
}
