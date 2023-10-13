package parser;

import lexer.*;
import parser.Decl.Decl;
import parser.Stmt.Stmt;

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
}
