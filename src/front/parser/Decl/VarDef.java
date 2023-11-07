package front.parser.Decl;

import errorHandle.Error;
import front.lexer.*;
import front.parser.Exp.AddExp;
import front.parser.Ident;
import front.parser.Init.InitVal;
import front.parser.Exp.ConstExp;
import front.symbolTable.Symbol;
import front.symbolTable.SymbolTable;
import front.symbolTable.Variate;

import java.util.ArrayList;

public class VarDef {
    private Ident ident;
    private ArrayList<ConstExp> constExps;
    private InitVal initVal;
    public VarDef() {
        ident = null;
        constExps = new ArrayList<>();
        initVal = null;
    }

    //VarDef → Ident { '[' ConstExp ']' } [ '=' InitVal ]
    public void parse() {
        ident = new Ident();
        ident.parse();
        if (SymbolTable.search(ident.getName(), true,true) != null) {
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
        if (SymbolTable.search(ident.getName(),true, true) == null) {
            Symbol symbol = new Variate(SymbolTable.curId, ident.getName(), dim, false);
            SymbolTable.getCurrentTable().directory.put(ident.getName(), symbol);
        }
        if (Lexer.getInstance().getLexType() != LexType.ASSIGN) {
            return;
        }
        Lexer.getInstance().next();
        initVal = new InitVal();
        initVal.parse();
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
        if (initVal != null) {
            sb.append("ASSIGN =\n");
            sb.append(initVal.toString());
        }
        sb.append("<VarDef>\n");
        return sb.toString();
    }

    public int getDim() {
        return constExps.size();
    }

    public String getIdent() {
        return ident.getName();
    }

    public InitVal getInitVal() {
        return initVal;
    }

    public AddExp getAddExp(int i) {
        return constExps.get(i).getAddExp();
    }
}
