package Parser.Exp;

import Lexer.*;
import Parser.FuncDef.FuncRParams;
import Parser.Ident;

public class UnaryExp {
    private PrimaryExp primaryExp;
    private Ident ident;
    private FuncRParams funcRParams;
    private UnaryOp unaryOp;
    private UnaryExp unaryExp;
    public UnaryExp() {
        primaryExp = null;
        ident = null;
        funcRParams = null;
        unaryOp = null;
        unaryExp = null;
    }

    //UnaryExp → PrimaryExp | Ident '(' [FuncRParams] ')' | UnaryOp UnaryExp
    public void parse() {
        if (Lexer.getInstance().getLexType() == LexType.IDENFR &&
            Lexer.getInstance().getNextWord(1) == LexType.LPARENT) {
            ident = new Ident();
            ident.parse();
            if (Lexer.getInstance().getLexType() != LexType.LPARENT) {
                System.out.println("UnaryExp error(");
                return; //error
            }
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() != LexType.RPARENT) {
                funcRParams = new FuncRParams();
                funcRParams.parse();
            }
            if (Lexer.getInstance().getLexType() != LexType.RPARENT) {
                System.out.println("UnaryExp error)");
                return; //error
            }
            Lexer.getInstance().next();
        } else if ("+-!".indexOf(Lexer.getInstance().getToken().charAt(0)) != -1) {
            unaryOp = new UnaryOp();
            unaryOp.parse();
            unaryExp = new UnaryExp();
            unaryExp.parse();
        } else {
            primaryExp = new PrimaryExp();
            primaryExp.parse();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (primaryExp != null) {
            sb.append(primaryExp);
        } else if (ident != null) {
            sb.append(ident);
            sb.append("LPARENT (\n");
            if (funcRParams != null) {
                sb.append(funcRParams);
            }
            sb.append("RPARENT )\n");
        } else {
            sb.append(unaryOp);
            sb.append(unaryExp);
        }
        sb.append("<UnaryExp>\n");
        return sb.toString();
    }
}
