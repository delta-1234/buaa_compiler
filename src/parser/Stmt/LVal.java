package parser.Stmt;

import errorHandle.Error;
import lexer.*;
import parser.Exp.Exp;
import parser.Ident;
import symbolTable.Symbol;
import symbolTable.SymbolTable;
import symbolTable.Variate;

import java.util.ArrayList;

public class LVal {
    private Ident ident;
    private ArrayList<Exp> exps;
    public LVal() {
        ident = null;
        exps = new ArrayList<>();
    }

    // LVal → Ident {'[' Exp ']'}
    public void parse() {
        ident = new Ident();
        ident.parse();
        if (SymbolTable.search(ident.getName(), false, true) == null) {
            Error.error('c', Lexer.getInstance().getLineNum());
        }
        while (true) {
            if (Lexer.getInstance().getLexType() != LexType.LBRACK) {
                break;
            }
            Lexer.getInstance().next();
            Exp exp = new Exp();
            exp.parse();
            exps.add(exp);
            if (Lexer.getInstance().getLexType() != LexType.RBRACK) {
                Error.error('k', Lexer.getInstance().getLastNum());
            } else {
                Lexer.getInstance().next();
            }
        }
    }

    public Ident getIdent() {
        return ident;
    }

    public int getDim() {
        Symbol symbol = SymbolTable.search(ident.getName(), false, true);
        if (symbol == null) {
            return 0;
        }
        return ((Variate)symbol).dim - exps.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident);
        for (int i = 0; i < exps.size(); i++) {
            sb.append("LBRACK [\n");
            sb.append(exps.get(i).toString());
            sb.append("RBRACK ]\n");
        }
        sb.append("<LVal>\n");
        return sb.toString();
    }
}
