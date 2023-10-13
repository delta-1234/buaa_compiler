package errorHandle;

import lexer.Lexer;

import java.util.ArrayList;
import java.util.HashSet;

public class Error {
    private static boolean detail = false;
    private static ArrayList<String> errorInform = new ArrayList<>();
    private static ArrayList<String> simpleInform = new ArrayList<>();
    private static HashSet<Integer> lineToError = new HashSet<>();
    private static String [] typeName = {"非法符号", "名字重定义", "未定义的名字", "函数参数个数不匹配",
        "函数参数类型不匹配", "无返回值的函数存在不匹配的return语句", "有返回值的函数缺少return语句", "不能改变常量的值",
    "缺少分号", "缺少右小括号’)’", "缺少右中括号’]’", "printf中格式字符与表达式个数不匹配", "在非循环块中使用break和continue语句"};

    public static void error(char type, int lineNum) {
        StringBuilder sb = new StringBuilder();
        if (detail) {
            sb.append("行号：");
            sb.append(lineNum + " ");
            sb.append("发生错误的字符串：");
            sb.append(Lexer.getInstance().getToken() + " ");
            sb.append("错误类型：");
            sb.append(typeName[type - 'a'] + " " + type);
            errorInform.add(sb.toString());
        } else {
            sb.append(lineNum + " " + type);
            errorInform.add(sb.toString());
        }
        if (!lineToError.contains(lineNum)) {
            simpleInform.add(sb.toString());
        }
        lineToError.add(lineNum);
    }

    public static ArrayList<String> getErrorInform() {
        if (detail) {
            return errorInform;
        }
        return simpleInform;
    }

}
