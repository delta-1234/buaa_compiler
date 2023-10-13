package parser.Exp;

import errorHandle.Error;
import lexer.LexType;
import lexer.Lexer;
import parser.FuncDef.FuncRParams;
import parser.Ident;
import parser.Stmt.LVal;
import symbolTable.Function;
import symbolTable.SymbolTable;

import java.util.ArrayList;
import java.util.Objects;

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
            Function func = (Function) SymbolTable.search(ident.getName(), false, false);
            if (func == null) {
                Error.error('c', Lexer.getInstance().getLineNum());
            }
            Lexer.getInstance().next();
            if (FuncRParams.firstSet().contains(Lexer.getInstance().getLexType())) {
                funcRParams = new FuncRParams();
                funcRParams.parse();
            }
            if (func != null) {
                if ((funcRParams != null && funcRParams.getParamNum() != func.paramNum) ||
                    (funcRParams == null && func.paramNum != 0)) {
                    Error.error('d', Lexer.getInstance().getLineNum());
                } else if (funcRParams != null) {
                    boolean flag = true;
                    ArrayList<Integer> tmp = funcRParams.getParamList();
                    for (int i = 0; i < func.paramList.size(); i++) {
                        if (!Objects.equals(func.paramList.get(i), tmp.get(i))) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) {
                        Error.error('e', Lexer.getInstance().getLineNum());
                    }
                }
            }
            if (Lexer.getInstance().getLexType() != LexType.RPARENT) {
                Error.error('j', Lexer.getInstance().getLastNum());
            } else {
                Lexer.getInstance().next();
            }
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

    public void parse(LVal lVal) {
        primaryExp = new PrimaryExp();
        primaryExp.parse(lVal);
    }

    public int getDim() {
        if (ident != null) {
            Function func = (Function) SymbolTable.search(ident.getName(), false, false);
            if (func != null && func.retype == 0) {
                return -1;
            }
            return 0;
        } else if (primaryExp == null) {
            return 0;
        }
        return primaryExp.getDim();
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
