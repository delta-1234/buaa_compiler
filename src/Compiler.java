import lexer.Lexer;
import parser.CompUnit;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import errorHandle.Error;

public class Compiler {
    public static void main(String[] args) {
        try {
            File file = new File("testfile.txt");
            Scanner scanner = new Scanner(file);
            String outputFilePath = "error.txt";
            FileWriter fileWriter = new FileWriter(outputFilePath, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            Lexer lexer = new Lexer(scanner);
            CompUnit compUnit = new CompUnit();
            compUnit.parse();
            for (int i = 0; i < Error.getErrorInform().size(); i++) {
                printWriter.println(Error.getErrorInform().get(i));
            }
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
