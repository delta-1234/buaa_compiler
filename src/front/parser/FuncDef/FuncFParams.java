package front.parser.FuncDef;

import front.lexer.*;

import java.util.ArrayList;
import java.util.HashSet;

public class FuncFParams {
    private static HashSet<LexType> firstSet = new HashSet<>();
    private ArrayList<FuncFParam> funcFParams;
    public FuncFParams() {
        funcFParams = new ArrayList<>();
    }

    // FuncFParams → FuncFParam { ',' FuncFParam }
    public void parse() {
        FuncFParam funcFParam = new FuncFParam();
        funcFParam.parse();
        funcFParams.add(funcFParam);
        while (true) {
            if (Lexer.getInstance().getLexType() != LexType.COMMA) {
                break;
            }
            Lexer.getInstance().next();
            FuncFParam tmp = new FuncFParam();
            tmp.parse();
            funcFParams.add(tmp);
        }
    }

    public int getParamNum() {
        return funcFParams.size();
    }

    public FuncFParam getParam(int i) {
        return funcFParams.get(i);
    }

    public ArrayList<Integer> getParamList() {
        ArrayList<Integer> paramList = new ArrayList<>();
        for (int i = 0; i < funcFParams.size(); i++) {
            paramList.add(funcFParams.get(i).getType());
        }
        return paramList;
    }

    public static HashSet<LexType> firstSet() {
        if (firstSet.size() == 0) {
            firstSet.add(LexType.INTTK);
        }
        return firstSet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(funcFParams.get(0).toString());
        for (int i = 1; i < funcFParams.size(); i++) {
            sb.append("COMMA ,\n");
            sb.append(funcFParams.get(i).toString());
        }
        sb.append("<FuncFParams>\n");
        return sb.toString();
    }
}
