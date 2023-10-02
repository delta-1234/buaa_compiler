package Parser;

import Lexer.*;
import Parser.Decl.Decl;
import Parser.Stmt.Stmt;

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

    @Override
    public String toString() {
        if (stmt != null) {
            return stmt.toString();
        }
        return decl.toString();
    }
}
