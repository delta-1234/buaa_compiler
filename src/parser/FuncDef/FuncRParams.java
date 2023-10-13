package parser.FuncDef;

import lexer.LexType;
import lexer.Lexer;
import parser.Exp.Exp;
import symbolTable.SymbolTable;

import java.util.ArrayList;
import java.util.HashSet;

public class FuncRParams {
    private ArrayList<Exp> exps;

    public FuncRParams() {
        exps = new ArrayList<>();
    }

    ////FuncRParams → Exp { ',' Exp }
    public void parse() {
        Exp exp = new Exp();
        exp.parse();
        exps.add(exp);
        while (true) {
            if (Lexer.getInstance().getLexType() != LexType.COMMA) {
                break;
            }
            Lexer.getInstance().next();
            Exp exp1 = new Exp();
            exp1.parse();
            exps.add(exp1);
        }
    }

    public int getParamNum() {
        return exps.size();
    }

    public ArrayList<Integer> getParamList() {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < exps.size(); i++) {
            tmp.add(exps.get(i).getDim());
        }
        return tmp;
    }

    public static HashSet<LexType> firstSet() {
        return Exp.firstSet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(exps.get(0));
        for (int i = 1; i < exps.size(); i++) {
            sb.append("COMMA ,\n");
            sb.append(exps.get(i));
        }
        sb.append("<FuncRParams>\n");
        return sb.toString();
    }
}
