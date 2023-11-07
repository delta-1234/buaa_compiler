import errorHandle.Error;
import front.lexer.Lexer;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import util.IRBuilder;
import util.Parser;

public class Compiler {
    public static void main(String[] args) {
        try {
            File file = new File("testfile.txt");
            Scanner scanner = new Scanner(file);
            String outputFilePath = "llvm_ir.txt";
            FileWriter fileWriter = new FileWriter(outputFilePath, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            Lexer lexer = new Lexer(scanner);
            Parser.compUnit.parse();
            if (Error.getErrorInform().size() == 0) {
                IRBuilder.buildIR();
            }
            printWriter.println(IRBuilder.module);
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
