package Parser.FuncDef;

import Lexer.*;

import java.util.ArrayList;

public class FuncFParams {
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
