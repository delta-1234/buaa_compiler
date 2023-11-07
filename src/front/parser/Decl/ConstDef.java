package front.parser.Decl;

import errorHandle.Error;
import front.lexer.*;
import front.parser.Exp.AddExp;
import front.parser.Exp.ConstExp;
import front.parser.Ident;
import front.parser.Init.ConstInitVal;
import front.symbolTable.Symbol;
import front.symbolTable.SymbolTable;
import front.symbolTable.Variate;

import java.util.ArrayList;

public class ConstDef {
    private Ident ident;
    private ArrayList<ConstExp> constExps;
    private ConstInitVal constInitVal;

    public ConstDef() {
        ident = null;
        constExps = new ArrayList<>();
        constInitVal = null;
    }

    //ConstDef → Ident { '[' ConstExp ']' } '=' ConstInitVal
    public void parse() {
        ident = new Ident();
        ident.parse();
        if (SymbolTable.search(ident.getName(), true, true) != null) {
            Error.error('b', Lexer.getInstance().getLineNum());
        }
        int dim = 0;
        while (true) {
            if (Lexer.getInstance().getLexType() != LexType.LBRACK) {
                break;
            }
            dim++;
            Lexer.getInstance().next();
            ConstExp constExp = new ConstExp();
            constExp.parse();
            constExps.add(constExp);
            if (Lexer.getInstance().getLexType() != LexType.RBRACK) {
                Error.error('k', Lexer.getInstance().getLastNum());
            } else {
                Lexer.getInstance().next();
            }
        }
        if (SymbolTable.search(ident.getName(), true, true) == null) {
            Symbol symbol = new Variate(SymbolTable.curId, ident.getName(), dim, true);
            SymbolTable.getCurrentTable().directory.put(ident.getName(), symbol);
        }
        Lexer.getInstance().next();
        constInitVal = new ConstInitVal();
        constInitVal.parse();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toString());
        for (int i = 0; i < constExps.size(); i++) {
            sb.append("LBRACK [\n");
            sb.append(constExps.get(i).toString());
            sb.append("RBRACK ]\n");
        }
        sb.append("ASSIGN =\n");
        sb.append(constInitVal.toString());
        sb.append("<ConstDef>\n");
        return sb.toString();
    }

    public String getIdent() {
        return ident.getName();
    }

    public int getDim() {
        return constExps.size();
    }

    public AddExp getAddExp(int i) {
        return constExps.get(i).getAddExp();
    }

    public ConstInitVal getConstInitVal() {
        return constInitVal;
    }
}
