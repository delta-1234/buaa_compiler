import backend.Mips;
import errorHandle.Error;
import front.lexer.Lexer;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import llvm.IRModule;
import util.IRBuilder;
import util.Parser;

public class Compiler {
    public static void main(String[] args) {
        try {
            File file = new File("testfile.txt");
            Scanner scanner = new Scanner(file);
            FileWriter llvmFileWriter = new FileWriter("llvm_ir.txt", false);
            PrintWriter llvmPrintWriter = new PrintWriter(llvmFileWriter);
            FileWriter mipsFileWriter = new FileWriter("mips.txt", false);
            PrintWriter mipsPrintWriter = new PrintWriter(mipsFileWriter);
            FileWriter errorFileWriter = new FileWriter("error.txt", false);
            PrintWriter errorPrintWriter = new PrintWriter(errorFileWriter);
            Lexer lexer = new Lexer(scanner);
            Parser.compUnit.parse();
            if (Error.getErrorInform().size() == 0) {
                IRBuilder.buildIR();
            } else {
                for (int i = 0; i < Error.getErrorInform().size(); i++) {
                    errorPrintWriter.println(Error.getErrorInform().get(i));
                }
                errorFileWriter.close();
                return;
            }
            IRBuilder.better();
            Mips mips = new Mips();
            mips.build();
            llvmPrintWriter.println(IRBuilder.module);
            llvmPrintWriter.close();
            mipsPrintWriter.println(mips);
            mipsFileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
