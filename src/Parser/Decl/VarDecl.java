package Parser.Decl;

import Lexer.*;

import java.util.ArrayList;

public class VarDecl {
    private ArrayList<VarDef> varDefs;
    public VarDecl() {
        varDefs = new ArrayList<>();
    }

    //VarDecl → BType VarDef { ',' VarDef } ';'
    public void parse() {
        if (Lexer.getInstance().getLexType() != LexType.INTTK) {
            System.out.println("VarDecl error");
            return; //error
        }
        Lexer.getInstance().next();
        VarDef varDef = new VarDef();
        varDef.parse();
        varDefs.add(varDef);
        while (true) {
            if (Lexer.getInstance().getLexType() != LexType.COMMA) {
                break;
            }
            Lexer.getInstance().next();
            VarDef tmp = new VarDef();
            tmp.parse();
            varDefs.add(tmp);
        }
        if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
            System.out.println("VarDecl error;");
            return; //error
        }
        Lexer.getInstance().next();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("INTTK int\n");
        sb.append(varDefs.get(0).toString());
        for (int i = 1; i < varDefs.size(); i++) {
            sb.append("COMMA ,\n");
            sb.append(varDefs.get(i).toString());
        }
        sb.append("SEMICN ;\n");
        sb.append("<VarDecl>\n");
        return sb.toString();
    }
}
