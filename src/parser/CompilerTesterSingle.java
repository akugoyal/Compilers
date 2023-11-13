package parser;

import ast.*;
import scanner.*;
import java.io.*;

public class CompilerTesterSingle
{
    public static void main(String[] args) throws ScanErrorException, InvalidOperator, IOException
    {
        String fileName = "src/parser/tests/compilerTests/cases/parserTest7.txt";


        Scanner s = new Scanner(new BufferedReader(new FileReader(fileName)));
        Parser p = new Parser(s, fileName);
        Program prog = p.parseProgram();
        prog.compile("src/parser/bytecode.asm");

        Runtime r = Runtime.getRuntime();
        Process process = r.exec("java -jar /Applications/Mars4_5.jar " +
                "/Users/akulgoyal/Desktop/Compilers/Compiler/src/parser/bytecode.asm");
        BufferedReader inp = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = inp.readLine()) != null) {
            System.out.println(line);
        }
    }
}
