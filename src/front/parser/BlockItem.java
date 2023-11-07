package front.parser;

import front.lexer.*;
import front.parser.Decl.Decl;
import front.parser.Stmt.Stmt;

public class BlockItem {
    private Decl decl;
    private Stmt stmt;
    public BlockItem() {
        decl = null;
        stmt = null;
    }

    //BlockItem → Decl | Stmt
    public void parse() {
        if (Lexer.getInstance().getLexType() != LexType.CONSTTK &&
            Lexer.getInstance().getLexType() != LexType.INTTK) {
            stmt = new Stmt();
            stmt.parse();
        } else {
            decl = new Decl();
            decl.parse();
        }
    }

    public boolean isReturn() {
        if (decl != null) {
            return false;
        } else {
            return stmt.isReturn();
        }
    }

    @Override
    public String toString() {
        if (stmt != null) {
            return stmt.toString();
        }
        return decl.toString();
    }

    public Decl getDecl() {
        return decl;
    }

    public Stmt getStmt() {
        return stmt;
    }
}
