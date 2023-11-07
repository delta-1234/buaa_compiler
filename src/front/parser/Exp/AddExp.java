package front.parser.Exp;

import front.lexer.LexType;
import front.lexer.Lexer;
import llvm.IRModule;
import front.parser.Stmt.LVal;

import java.util.ArrayList;

public class AddExp {
    private ArrayList<MulExp> mulExps;
    private ArrayList<Boolean> isNeg;

    public AddExp() {
        mulExps = new ArrayList<>();
        isNeg = new ArrayList<>();
    }


    public void parse() {
        MulExp mulExp = new MulExp();
        mulExp.parse();
        isNeg.add(false);
        mulExps.add(mulExp);
        while (true) {
            if (Lexer.getInstance().getLexType() == LexType.PLUS) {
                isNeg.add(false);
            } else if (Lexer.getInstance().getLexType() == LexType.MINU) {
                isNeg.add(true);
            } else {
                break;
            }
            Lexer.getInstance().next();
            MulExp tmp = new MulExp();
            tmp.parse();
            mulExps.add(tmp);
        }
    }

    public void parse(LVal lVal) {
        MulExp mulExp = new MulExp();
        mulExp.parse(lVal);
        isNeg.add(false);
        mulExps.add(mulExp);
        while (true) {
            if (Lexer.getInstance().getLexType() == LexType.PLUS) {
                isNeg.add(false);
            } else if (Lexer.getInstance().getLexType() == LexType.MINU) {
                isNeg.add(true);
            } else {
                break;
            }
            Lexer.getInstance().next();
            MulExp tmp = new MulExp();
            tmp.parse();
            mulExps.add(tmp);
        }
    }

    public int getDim() {
        if (mulExps.size() > 1) {
            return 0;
        }
        return mulExps.get(0).getDim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mulExps.size() - 1; i++) {
            sb.append(mulExps.get(i));
            sb.append("<AddExp>\n");
            if (isNeg.get(i + 1)) {
                sb.append("MINU -\n");
            } else {
                sb.append("PLUS +\n");
            }
        }
        sb.append(mulExps.get(mulExps.size() - 1));
        sb.append("<AddExp>\n");
        return sb.toString();
    }

    public void sort(IRModule module) {
        for (int i = 0; i < mulExps.size(); i++) {
            if (mulExps.get(i).isConst(module)) {
                continue;
            }
            boolean flag = false;
            for (int j = i + 1; j < mulExps.size(); j++) {
                if (mulExps.get(j).isConst(module)) {
                    MulExp temp = mulExps.get(j);
                    mulExps.set(j, mulExps.get(i));
                    mulExps.set(i, temp);
                    boolean t = isNeg.get(j);
                    isNeg.set(j, isNeg.get(i));
                    isNeg.set(i, t);
                    flag = true;
                }
            }
            if (!flag) {
                return;
            }
        }
    }

    public MulExp getMulExp(int i) {
        return mulExps.get(i);
    }

    public int getMulNum() {
        return mulExps.size();
    }

    public boolean isNeg(int i) {
        return isNeg.get(i);
    }

    public boolean isConst(IRModule module) {
        for (int i = 0; i < mulExps.size(); i++) {
            if (!mulExps.get(i).isConst(module)) {
                return false;
            }
        }
        return true;
    }

    public int cal(IRModule module) {
        int temp = mulExps.get(0).cal(module);
        for (int i = 1; i < mulExps.size(); i++) {
            if (isNeg.get(i)) {
                temp -= mulExps.get(i).cal(module);
            } else {
                temp += mulExps.get(i).cal(module);
            }
        }
        return temp;
    }

    public boolean hasFunc() {
        for (int i = 0; i < mulExps.size(); i++) {
            if (mulExps.get(i).hasFunc()) {
                return true;
            }
        }
        return false;
    }
}
