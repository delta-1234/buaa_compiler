package parser.FuncDef;

import errorHandle.Error;
import lexer.LexType;
import lexer.Lexer;
import parser.Exp.ConstExp;
import parser.Ident;
import symbolTable.Symbol;
import symbolTable.SymbolTable;
import symbolTable.Variate;

import java.util.ArrayList;

public class FuncFParam {
    private Ident ident;
    private boolean isArray;
    private int dim;
    private ArrayList<ConstExp> constExps;

    public FuncFParam() {
        ident = null;
        isArray = false;
        dim = 0;
        constExps = new ArrayList<>();
    }

    //FuncFParam → BType Ident ['[' ']' { '[' ConstExp ']' }]
    public void parse() {
        Lexer.getInstance().next();
        ident = new Ident();
        ident.parse();
        if (Lexer.getInstance().getLexType() == LexType.LBRACK) {
            isArray = true;
            dim = 1;
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() != LexType.RBRACK) {
                Error.error('k', Lexer.getInstance().getLastNum());
            } else {
                Lexer.getInstance().next();
            }
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
        }
        if (!SymbolTable.getNextTable().directory.containsKey(ident.getName())) {
            Symbol symbol = new Variate(SymbolTable.curId, ident.getName(), dim, false);
            SymbolTable.getNextTable().directory.put(ident.getName(), symbol);
        } else {
            Error.error('b', Lexer.getInstance().getLineNum());
        }
    }

    public int getType() {
        return dim;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("INTTK int\n");
        sb.append(ident);
        if (isArray) {
            sb.append("LBRACK [\n");
            sb.append("RBRACK ]\n");
            for (int i = 0; i < constExps.size(); i++) {
                sb.append("LBRACK [\n");
                sb.append(constExps.get(i).toString());
                sb.append("RBRACK ]\n");
            }
        }
        sb.append("<FuncFParam>\n");
        return sb.toString();
    }
}
