package front.parser.Exp;

import front.lexer.LexType;
import front.lexer.Lexer;
import llvm.IRModule;
import front.parser.Stmt.LVal;

import java.util.ArrayList;

public class MulExp {
    private ArrayList<UnaryExp> unaryExps;
    private ArrayList<Integer> op;

    public MulExp() {
        unaryExps = new ArrayList<>();
        op = new ArrayList<>();
    }

    public void parse() {
        UnaryExp unaryExp = new UnaryExp();
        unaryExp.parse();
        op.add(1);
        unaryExps.add(unaryExp);
        while (true) {
            if (Lexer.getInstance().getLexType() == LexType.MULT) {
                op.add(1);
            } else if (Lexer.getInstance().getLexType() == LexType.MOD) {
                op.add(2);
            } else if (Lexer.getInstance().getLexType() == LexType.DIV) {
                op.add(3);
            } else {
                break;
            }
            Lexer.getInstance().next();
            UnaryExp tmp = new UnaryExp();
            tmp.parse();
            unaryExps.add(tmp);
        }
    }

    public void parse(LVal lVal) {
        UnaryExp unaryExp = new UnaryExp();
        unaryExp.parse(lVal);
        op.add(1);
        unaryExps.add(unaryExp);
        while (true) {
            if (Lexer.getInstance().getLexType() == LexType.MULT) {
                op.add(1);
            } else if (Lexer.getInstance().getLexType() == LexType.MOD) {
                op.add(2);
            } else if (Lexer.getInstance().getLexType() == LexType.DIV) {
                op.add(3);
            } else {
                break;
            }
            Lexer.getInstance().next();
            UnaryExp tmp = new UnaryExp();
            tmp.parse();
            unaryExps.add(tmp);
        }
    }

    public int getDim() {
        if (unaryExps.size() > 1) {
            return 0;
        }
        return unaryExps.get(0).getDim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < unaryExps.size() - 1; i++) {
            sb.append(unaryExps.get(i).toString());
            sb.append("<MulExp>\n");
            if (op.get(i+1) == 1) {
                sb.append("MULT *\n");
            } else if (op.get(i+1) == 2) {
                sb.append("MOD %\n");
            } else {
                sb.append("DIV /\n");
            }
        }
        sb.append(unaryExps.get(unaryExps.size() - 1));
        sb.append("<MulExp>\n");
        return sb.toString();
    }

    public UnaryExp getUnaryExp(int i) {
        return unaryExps.get(i);
    }

    public int getUnaryNum() {
        return unaryExps.size();
    }

    public int getOp(int i) {
        return op.get(i);
    }

    public boolean isConst(IRModule module) {
        for (int i = 0; i < unaryExps.size(); i++) {
            if (!unaryExps.get(i).isConst(module)) {
                return false;
            }
        }
        return true;
    }

    public int cal(IRModule module) {
        int temp = unaryExps.get(0).cal(module);
        for (int i = 1; i < unaryExps.size(); i++) {
            if (op.get(i) == 1) {
                temp *= unaryExps.get(i).cal(module);
            } else if (op.get(i) == 2){
                temp %= unaryExps.get(i).cal(module);
            } else {
                temp /= unaryExps.get(i).cal(module);
            }
        }
        return temp;
    }

    public boolean hasFunc() {
        for (int i = 0; i < unaryExps.size(); i++) {
            if (unaryExps.get(i).hasFunc()) {
                return true;
            }
        }
        return false;
    }
}
