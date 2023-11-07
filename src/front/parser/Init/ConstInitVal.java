package front.parser.Init;

import front.lexer.*;
import front.parser.Exp.AddExp;
import front.parser.Exp.ConstExp;

import java.util.ArrayList;

public class ConstInitVal {
    private ConstExp constExp;
    private ArrayList<ConstInitVal> constInitVals;
    public ConstInitVal() {
        constExp = null;
        constInitVals = new ArrayList<>();
    }

    //ConstInitVal -> ConstExp | '{' [ ConstInitVal { ',' ConstInitVal } ] '}'
    public void parse() {
        if (Lexer.getInstance().getLexType() == LexType.LBRACE) {
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() == LexType.RBRACE) {
                Lexer.getInstance().next();
                return;
            }
            ConstInitVal constInitVal = new ConstInitVal();
            constInitVal.parse();
            constInitVals.add(constInitVal);
            while (true) {
                if (Lexer.getInstance().getLexType() != LexType.COMMA) {
                    break;
                }
                Lexer.getInstance().next();
                ConstInitVal tmp = new ConstInitVal();
                tmp.parse();
                constInitVals.add(tmp);
            }
            if (Lexer.getInstance().getLexType() != LexType.RBRACE) {
                System.out.println("ConstInitVal error}");
                return;//error
            }
            Lexer.getInstance().next();
        } else {
            constExp = new ConstExp();
            constExp.parse();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (constExp != null) {
            sb.append(constExp.toString());
        } else {
            sb.append("LBRACE {\n");
            if (constInitVals.size() != 0) {
                sb.append(constInitVals.get(0).toString());
            }
            for (int i = 1; i < constInitVals.size(); i++) {
                sb.append("COMMA ,\n");
                sb.append(constInitVals.get(i));
            }
            sb.append("RBRACE }\n");
        }
        sb.append("<ConstInitVal>\n");
        return sb.toString();
    }

    public AddExp getAddExp() {
        return constExp.getAddExp();
    }

    public ConstInitVal getConstInitVal(int i) {
        return constInitVals.get(i);
    }

    public int getInitNum() {
        return constInitVals.size();
    }
}
