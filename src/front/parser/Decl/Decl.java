package front.parser.Decl;

import front.lexer.Lexer;
import front.lexer.LexType;

public class Decl {
    private ConstDecl constDecl;
    private VarDecl varDecl;
    public Decl() {
        constDecl = null;
        varDecl = null;
    }

    //Decl → ConstDecl | VarDecl
    public void parse() {
        if (Lexer.getInstance().getLexType() == LexType.CONSTTK) {
            constDecl = new ConstDecl();
            constDecl.parse();
        } else {
            varDecl = new VarDecl();
            varDecl.parse();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (constDecl != null) {
            sb.append(constDecl.toString());
        } else {
            sb.append(varDecl.toString());
        }
        return sb.toString();
    }

    public boolean isConst() {
        if (constDecl != null) {
            return true;
        }
        return false;
    }

    public ConstDecl getConstDecl() {
        return constDecl;
    }

    public VarDecl getVarDecl() {
        return varDecl;
    }
}

