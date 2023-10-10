import Lexer.Lexer;
import Parser.CompUnit;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Compiler {
    public static void main(String[] args) {
        try {
            File file = new File("testfile.txt");
            Scanner scanner = new Scanner(file);
            String outputFilePath = "output.txt";
            FileWriter fileWriter = new FileWriter(outputFilePath, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            Lexer lexer = new Lexer(scanner);
            CompUnit compUnit = new CompUnit();
            compUnit.parse();
            printWriter.print(compUnit);
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
