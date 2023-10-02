package Parser.Stmt;

import Lexer.*;
import Parser.Block;
import Parser.Exp.Exp;

import java.util.ArrayList;

public class Stmt {
    private LVal lVal;
    private Block block;
    private Cond cond;
    private ArrayList<Exp> exps;
    private ForStmt forStmt1;
    private ForStmt forStmt2;
    private ArrayList<Stmt> stmts;
    private FormatString formatString;
    private int num; //第几种情況
    public Stmt() {
        lVal = null;
        cond = null;
        exps = new ArrayList<>();
        forStmt1 = null;
        forStmt2 = null;
        stmts = new ArrayList<>();
        formatString = null;
        num = 0;
    }

    /*
    Stmt → LVal '=' Exp ';'                                 (1)
    | [Exp] ';'                                             (2)
    | Block                                                 (3)
    | 'if' '(' Cond ')' Stmt [ 'else' Stmt ]                (4)
    | 'for' '(' [ForStmt] ';' [Cond] ';' [forStmt] ')' Stmt (5)
    | 'break' ';'                                           (6)
    | 'continue' ';'                                        (7)
    | 'return' [Exp] ';'                                    (8)
    | LVal '=' 'getint''('')'';'                            (9)
    | 'printf''('FormatString{','Exp}')'';'                 (10)
     */
    public void parse() {
        if (Lexer.getInstance().getLexType() == LexType.IDENFR && Lexer.getInstance().isLVal()) {
            lVal = new LVal();
            lVal.parse();
            if (Lexer.getInstance().getLexType() != LexType.ASSIGN) {
                System.out.println("Stmt error=");
                return; //error
            }
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() == LexType.GETINTTK) {
                num = 9;
                Lexer.getInstance().next();
                if (Lexer.getInstance().getLexType() != LexType.LPARENT) {
                    System.out.println("Stmt error(");
                    return; //error
                }
                Lexer.getInstance().next();
                if (Lexer.getInstance().getLexType() != LexType.RPARENT) {
                    System.out.println("Stmt error)");
                    return; //error
                }
                Lexer.getInstance().next();
                if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
                    System.out.println("Stmt error;");
                    return; //error
                }
                Lexer.getInstance().next();
            } else {
                num = 1;
                Exp exp = new Exp();
                exp.parse();
                exps.add(exp);
                if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
                    System.out.println("Stmt error;");
                    return; //error
                }
                Lexer.getInstance().next();
            }
        } else if (Lexer.getInstance().getLexType() == LexType.LBRACE) {
            num = 3;
            block = new Block();
            block.parse();
        } else if (Lexer.getInstance().getLexType() == LexType.IFTK) {
            num = 4;
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() != LexType.LPARENT) {
                System.out.println("Stmt error(");
                return; //error
            }
            Lexer.getInstance().next();
            cond = new Cond();
            cond.parse();
            if (Lexer.getInstance().getLexType() != LexType.RPARENT) {
                System.out.println("Stmt error)");
                return; //error
            }
            Lexer.getInstance().next();
            Stmt stmt = new Stmt();
            stmt.parse();
            stmts.add(stmt);
            if (Lexer.getInstance().getLexType() == LexType.ELSETK) {
                Lexer.getInstance().next();
                Stmt stmt1 = new Stmt();
                stmt1.parse();
                stmts.add(stmt1);
            }
        } else if (Lexer.getInstance().getLexType() == LexType.FORTK) {
            num = 5;
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() != LexType.LPARENT) {
                System.out.println("Stmt error(");
                return; //error
            }
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
                forStmt1 = new ForStmt();
                forStmt1.parse();
            }
            if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
                System.out.println("Stmt error;");
                return;//error
            }
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
                cond = new Cond();
                cond.parse();
            }
            if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
                System.out.println("Stmt error;");
                return;//error
            }
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() != LexType.RPARENT) {
                forStmt2 = new ForStmt();
                forStmt2.parse();
            }
            if (Lexer.getInstance().getLexType() != LexType.RPARENT) {
                System.out.println("Stmt error)");
                return;//error
            }
            Lexer.getInstance().next();
            Stmt stmt = new Stmt();
            stmt.parse();
            stmts.add(stmt);
        } else if (Lexer.getInstance().getLexType() == LexType.BREAKTK) {
            num= 6;
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
                System.out.println("Stmt error;");
                return; //error
            }
            Lexer.getInstance().next();
        } else if (Lexer.getInstance().getLexType() == LexType.CONTINUETK) {
            num = 7;
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
                System.out.println("Stmt error;");
                return; //error
            }
            Lexer.getInstance().next();
        } else if (Lexer.getInstance().getLexType() == LexType.RETURNTK) {
            num = 8;
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
                Exp exp = new Exp();
                exp.parse();
                exps.add(exp);
            }
            if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
                System.out.println("Stmt error;");
                return; //error
            }
            Lexer.getInstance().next();
        } else if (Lexer.getInstance().getLexType() == LexType.PRINTFTK) {
            num = 10;
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() != LexType.LPARENT) {
                System.out.println("Stmt error(");
                return; //error
            }
            Lexer.getInstance().next();
            formatString = new FormatString();
            formatString.parse();
            while (true) {
                if (Lexer.getInstance().getLexType() != LexType.COMMA) {
                    break;
                }
                Lexer.getInstance().next();
                Exp exp = new Exp();
                exp.parse();
                exps.add(exp);
            }
            if (Lexer.getInstance().getLexType() != LexType.RPARENT) {
                System.out.println("Stmt error)");
                return; //error
            }
            Lexer.getInstance().next();
            if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
                System.out.println("Stmt error;");
                return; //error
            }
            Lexer.getInstance().next();
        } else {
            num = 2;
            if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
                Exp exp = new Exp();
                exp.parse();
                exps.add(exp);
            }
            if (Lexer.getInstance().getLexType() != LexType.SEMICN) {
                System.out.println("Stmt error;");
                return; //error
            }
            Lexer.getInstance().next();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (num) {
            case 1:
                sb.append(lVal);
                sb.append("ASSIGN =\n");
                sb.append(exps.get(0).toString());
                sb.append("SEMICN ;\n");
                break;
            case 2:
                if (exps.size() != 0) {
                    sb.append(exps.get(0).toString());
                }
                sb.append("SEMICN ;\n");
                break;
            case 3:
                sb.append(block);
                break;
            case 4:
                sb.append("IFTK if\n");
                sb.append("LPARENT (\n");
                sb.append(cond);
                sb.append("RPARENT )\n");
                sb.append(stmts.get(0).toString());
                if (stmts.size() > 1) {
                    sb.append("ELSETK else\n");
                    sb.append(stmts.get(1).toString());
                }
                break;
            case 5:
                sb.append("FORTK for\n");
                sb.append("LPARENT (\n");
                if (forStmt1 != null) {
                    sb.append(forStmt1);
                }
                sb.append("SEMICN ;\n");
                if (cond != null) {
                    sb.append(cond);
                }
                sb.append("SEMICN ;\n");
                if (forStmt2 != null) {
                    sb.append(forStmt2);
                }
                sb.append("RPARENT )\n");
                sb.append(stmts.get(0));
                break;
            case 6:
                sb.append("BREAKTK break\n");
                sb.append("SEMICN ;\n");
                break;
            case 7:
                sb.append("CONTINUETK continue\n");
                sb.append("SEMICN ;\n");
                break;
            case 8:
                sb.append("RETURNTK return\n");
                if (exps.size() != 0) {
                    sb.append(exps.get(0));
                }
                sb.append("SEMICN ;\n");
                break;
            case 9:
                sb.append(lVal);
                sb.append("ASSIGN =\n");
                sb.append("GETINTTK getint\n");
                sb.append("LPARENT (\n");
                sb.append("RPARENT )\n");
                sb.append("SEMICN ;\n");
                break;
            case 10:
                sb.append("PRINTFTK printf\n");
                sb.append("LPARENT (\n");
                sb.append(formatString);
                for (int i = 0; i < exps.size(); i++) {
                    sb.append("COMMA ,\n");
                    sb.append(exps.get(i));
                }
                sb.append("RPARENT )\n");
                sb.append("SEMICN ;\n");
                break;
        }
        sb.append("<Stmt>\n");
        return sb.toString();
    }
}
