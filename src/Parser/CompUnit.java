package Parser;

import Lexer.Lexer;
import Lexer.LexType;
import Parser.Decl.Decl;
import Parser.FuncDef.FuncDef;

import java.util.ArrayList;

public class CompUnit {
    private ArrayList<Decl> decls;
    private ArrayList<FuncDef> funcDefs;
    private MainFuncDef mainFuncDef;
    public CompUnit() {
        decls = new ArrayList<>();
        funcDefs = new ArrayList<>();
    }

    //CompUnit → {Decl} {FuncDef} MainFuncDef
    public void parse() {
        while (true) { //解析{Decl}
            if (Lexer.getInstance().getLexType() == LexType.CONSTTK) {
            } else if (Lexer.getInstance().getLexType() == LexType.VOIDTK) {
                break;
            } else if (Lexer.getInstance().getNextWord(1) == LexType.MAINTK) {
                break;
            } else if (Lexer.getInstance().getNextWord(2) == LexType.LPARENT) {
                break;
            } else if (Lexer.getInstance().getLexType() == LexType.WRONG) {
                break;
            }
            Decl decl = new Decl();
            decl.parse();
            decls.add(decl);
        }
        while (true) { //解析 {FuncDef}
            if (Lexer.getInstance().getLexType() == LexType.VOIDTK) {
            } else if (Lexer.getInstance().getNextWord(1) == LexType.MAINTK) {
                break;
            } else if (Lexer.getInstance().getLexType() == LexType.WRONG) {
                break;
            }
            FuncDef funcDef = new FuncDef();
            funcDef.parse();
            funcDefs.add(funcDef);
        }
        mainFuncDef = new MainFuncDef();
        mainFuncDef.parse();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < decls.size(); i++) {
            sb.append(decls.get(i).toString());
        }
        for (int i = 0; i < funcDefs.size(); i++) {
            sb.append(funcDefs.get(i).toString());
        }
        sb.append(mainFuncDef.toString());
        sb.append("<CompUnit>\n");
        return sb.toString();
    }
}
