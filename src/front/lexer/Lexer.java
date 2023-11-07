package front.lexer;

import errorHandle.Error;

import java.util.Scanner;


public class Lexer {
    private String[] reserveWords = {"main", "const", "int", "break",
        "continue", "if", "else", "for", "getint", "printf", "return", "void"};
    private LexType[] lexTypes = LexType.values();
    private Scanner sc;
    private StringBuilder source;
    private StringBuilder token;
    private LexType lexType;
    private int lineNum;
    private int lastNum;
    private int curPos;
    private int state;
    private static Lexer lexer = null;

    public Lexer(Scanner sc) {
        this.sc = sc;
        token = new StringBuilder();
        lexType = LexType.WRONG;
        lineNum = 1;
        lastNum = 0;
        curPos = 0;
        state = 0;
        source = new StringBuilder();
        while (sc.hasNext()) {
            source.append(sc.nextLine());
            source.append('\n');
        }
        sc.close();
        lexer = this;
        next(); // 创建时预读一个单词
    }

    public static Lexer getInstance() {
        return lexer;
    }

    public boolean next() {
        lastNum = lineNum;
        token.setLength(0);
        lexType = LexType.WRONG;
        char c;
        while (true) {
            if (curPos >= source.length()) {
                return false;
            }
            c = source.charAt(curPos);
            switch (state) {
                case 0:
                    if (Character.isLetter(c) || c == '_') {
                        state = 1;
                        token.append(c);
                        curPos++;
                    } else if (Character.isDigit(c)) {
                        state = 2;
                        token.append(c);
                        curPos++;
                        lexType = LexType.INTCON;
                    } else if (c == '&') {
                        state = 3;
                        token.append(c);
                        curPos++;
                    } else if ("!<>=".indexOf(c) != -1) {
                        state = 4;
                        token.append(c);
                        curPos++;
                    } else if (c == '|') {
                        state = 5;
                        token.append(c);
                        curPos++;
                    } else if (c == '"') {
                        state = 6;
                        token.append(c);
                        curPos++;
                        lexType = LexType.STRCON;
                    } else if (c == '/') {
                        state = 7;
                        curPos++;
                    } else if ("+-*%;,()[]{}".indexOf(c) != -1) {
                        state = 14;
                        token.append(c);
                        curPos++;
                    } else if (c == ' ' || c == '\t' || c == '\r') {
                        state = 0;
                        curPos++;
                    } else if (c == '\n') {
                        state = 0;
                        lineNum++;
                        curPos++;
                    } else {
                        state = 666;
                    }
                    break;
                case 1:
                    if (Character.isLetterOrDigit(c) || c == '_') {
                        token.append(c);
                        curPos++;
                    } else {
                        state = 14;
                    }
                    break;
                case 2:
                    if (Character.isDigit(c)) {
                        token.append(c);
                        curPos++;
                    } else {
                        state = 14;
                    }
                    break;
                case 3:
                    if (c == '&') {
                        token.append(c);
                        curPos++;
                        state = 14;
                        lexType = LexType.AND;
                    } else {
                        state = 666;
                    }
                    break;
                case 4:
                    if (c == '=') {
                        token.append(c);
                        curPos++;
                        state = 14;
                        if (token.charAt(0) == '<') {
                            lexType = LexType.LEQ;
                        } else if (token.charAt(0) == '>') {
                            lexType = LexType.GEQ;
                        } else if (token.charAt(0) == '=') {
                            lexType = LexType.EQL;
                        } else {
                            lexType = LexType.NEQ;
                        }
                    } else {
                        state = 14;
                    }
                    break;
                case 5:
                    if (c == '|') {
                        token.append(c);
                        curPos++;
                        state = 14;
                        lexType = LexType.OR;
                    } else {
                        state = 666;
                    }
                    break;
                case 6:
                    if (c == '"') {
                        token.append('"');
                        curPos++;
                        state = 14;
                    } else {
                        if (" !".indexOf(c) != -1) {
                        } else if (c == '%' && source.charAt(curPos+1) == 'd') {
                        } else if (c == 92 && source.charAt(curPos+1) == 'n') {
                        } else if (c >= 40 && c <= 126 && c != 92) {
                        } else {
                            Error.error('a', lineNum);
                        }
                        token.append(c);
                        curPos++;
                    }
                    break;
                case 7:
                    if (c == '/') {
                        state = 8;
                        curPos++;
                    } else if (c == '*') {
                        state = 9;
                        curPos++;
                    } else {
                        token.append('/');
                        state = 14;
                        lexType = LexType.DIV;
                    }
                    break;
                case 8:
                    if (c == '\n') {
                        state = 0;
                        curPos++;
                        lineNum++;
                    } else {
                        curPos++;
                    }
                    break;
                case 9:
                    if (c == '*') {
                        state = 10;
                        curPos++;
                    } else if (c == '\n') {
                        lineNum++;
                        curPos++;
                    } else {
                        curPos++;
                    }
                    break;
                case 10:
                    if (c == '/') {
                        state = 0;
                        curPos++;
                    } else if (c == '*') {
                        curPos++;
                    } else if (c == '\n') {
                        state = 9;
                        lineNum++;
                        curPos++;
                    } else {
                        state = 9;
                        curPos++;
                    }
                    break;
                case 14:
                    state = 0;
                    return true;
                case 666:
                    state = 0;
                    curPos++;
                    return true;
                default:
            }
        }
    }

    public String getToken() {
        return token.toString();
    }

    public LexType getLexType() {
        if (lexType != LexType.WRONG || token.isEmpty()) {
            return lexType;
        } else {
            char c = token.charAt(0);
            if (c == '_') {
                return LexType.IDENFR;
            } else if (Character.isLetter(c)) {
                int temp = -1;
                for (int i = 0; i < reserveWords.length; i++) {
                    if (token.toString().equals(reserveWords[i])) {
                        temp = i;
                        break;
                    }
                }
                if (temp != -1) {
                    return lexTypes[temp];
                } else {
                    return LexType.IDENFR;
                }
            } else if (c == '!') {
                return LexType.NOT;
            } else if (c == '+') {
                return LexType.PLUS;
            } else if (c == '-') {
                return LexType.MINU;
            } else if (c == '*') {
                return LexType.MULT;
            } else if (c == '%') {
                return LexType.MOD;
            } else if (c == '<') {
                return LexType.LSS;
            } else if (c == '>') {
                return LexType.GRE;
            } else if (c == '=') {
                return LexType.ASSIGN;
            } else if (c == ';') {
                return LexType.SEMICN;
            } else if (c == ',') {
                return LexType.COMMA;
            } else if (c == '(') {
                return LexType.LPARENT;
            } else if (c == ')') {
                return LexType.RPARENT;
            } else if (c == '[') {
                return LexType.LBRACK;
            } else if (c == ']') {
                return LexType.RBRACK;
            } else if (c == '{') {
                return LexType.LBRACE;
            } else {
                return LexType.RBRACE;
            }
        }
    }

    public LexType getNextWord(int num) {
        StringBuilder token = new StringBuilder(this.token);
        LexType lexType = this.lexType;
        int lineNum = this.lineNum;
        int lastNum = this.lastNum;
        int curPos = this.curPos;
        int state = this.state;
        for (int i = 0; i < num; i++) {
            next();
        }
        LexType tmp = getLexType();
        this.token = token;
        this.lexType = lexType;
        this.lineNum = lineNum;
        this.lastNum = lastNum;
        this.curPos = curPos;
        this.state = state;
        return tmp;
    }

    public int getLineNum() {
        return lineNum;
    }

    public int getLastNum() {
        return lastNum;
    }
}
