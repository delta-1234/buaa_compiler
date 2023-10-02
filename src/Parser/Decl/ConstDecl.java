package Parser.Decl;

import Lexer.Lexer;
import Lexer.LexType;

import java.util.ArrayList;

public class ConstDecl {
    private ArrayList<ConstDef> constDefs;

    public ConstDecl() {
        constDefs = new ArrayList<>();
    }

    //ConstDecl → 'const' BType ConstDef { ',' ConstDef } ';'
    public void parse() {
        if (Lexer.getInstance().getLexType() != LexType.CONSTTK) {
            System.out.println("ConstDecl error");
            return; //error
        }
        Lexer.getInstance().next();
        if (Lexer.getInstance().getLexType() != LexType.INTTK) {
            System.out.println("ConstDecl error");
            return; //error
        }
        Lexer.getInstance().next();
        ConstDef constDef = new ConstDef();
        constDef.parse();
        constDefs.add(constDef);
        while (true) {
            if (Lexer.getInstance().getLexType() != LexType.COMMA) {
                break;
            }
            Lexer.getInstance().next();
            ConstDef tmp = new ConstDef();
            tmp.parse();
            constDefs.add(tmp);
        }
        if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
            System.out.println("ConstDecl error");
            return; //error
        }
        Lexer.getInstance().next();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CONSTTK const\n");
        sb.append("INTTK int\n");
        sb.append(constDefs.get(0).toString());
        for (int i = 1; i < constDefs.size(); i++) {
            sb.append("COMMA ,\n");
            sb.append(constDefs.get(i));
        }
        sb.append("SEMICN ;\n");
        sb.append("<ConstDecl>\n");
        return sb.toString();
    }
}
