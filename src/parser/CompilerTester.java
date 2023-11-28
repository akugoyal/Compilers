package parser;

import ast.*;
import environment.*;
import scanner.*;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.*;

/**
 * CompilerTester is a class that tests the compiler by compiling multiple test cases and
 * comparing the output to the expected output. The test cases are stored in the cases folder. It
 * can also be used to test the compiler by writing the output to stdout.
 *
 * @author Akul Goyal
 * @version 11-18-2023
 */
public class CompilerTester
{
    /**
     * Main method that runs the compiler tester
     *
     * @param args command line arguments
     * @throws IOException        if file not found
     * @throws ScanErrorException if scan error
     */
    public static void main(String[] args) throws IOException, ScanErrorException
    {
        double start = System.nanoTime();
        String output = "file";               //set to "file" to test output and write to file

        if (output.equals("stdout"))
        {
            System.out.println("Writing output to stdout...");
            execAll();
        }
        else if (output.equals("file"))     //Writes answers to file and compares against solutions
        {
            System.out.println("Testing files...");
            testAll();
        }
        System.out.println("Test took " + (System.nanoTime() - start) / (1000000000) +
                " seconds.");
    }

    /**
     * Tests all the test cases by compiling and executing them and then comparing the output to
     * the expected output.
     *
     * @throws IOException        if file not found
     * @throws ScanErrorException if scan error
     */
    private static void testAll() throws IOException, ScanErrorException
    {
        int numCases = new File("src/parser/tests/compilerTests/cases").listFiles().length;
        String fileName = "src/parser/tests/compilerTests/cases/parserTest";
        String tempName = "src/parser/tests/compilerTests/temp/parserTest";
        String solName = "src/parser/tests/compilerTests/solutions/parserTest";
        clearFiles(tempName, numCases);
        for (int i = 0; i < numCases; i++)
        {
            System.out.println("Testing Case #" + i + "...");
            Scanner s = new Scanner(new BufferedReader(new FileReader(fileName + i + ".txt")));

            Parser p = new Parser(s, tempName + i + ".txt");
            Program prog = p.parseProgram();
            try
            {
                System.out.println("Compiling...");
                prog.compile("src/parser/bytecode.asm");
                System.out.println("Executing...");
                Runtime r = Runtime.getRuntime();
                Process process = r.exec("java -jar /Applications/Mars4_5.jar " +
                        "/Users/akulgoyal/Desktop/Compilers/Compiler/src/parser/bytecode.asm");
                BufferedReader inp = new BufferedReader(
                        new InputStreamReader(
                                process.getInputStream()));
                BufferedWriter bw = new BufferedWriter(
                        new FileWriter(
                                tempName + i + ".txt"));
                String line;
                inp.readLine();
                inp.readLine();
                ArrayList<String> lines = new ArrayList<String>();
                while ((line = inp.readLine()) != null)
                {
                    lines.add(line);
                }
                lines.remove(lines.size() - 1);
                for (String l : lines)
                {
                    bw.write(l + "\n");
                }
                bw.close();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        boolean good = true;
        for (int i = 0; i < numCases; i++)
        {
            System.out.println("Verifying output for Case #" + i + "...");
            File temp = new File(tempName + i + ".txt");
            File sol = new File(solName + i + ".txt");
            byte[] tempArr = Files.readAllBytes(temp.toPath());
            byte[] solArr = Files.readAllBytes(sol.toPath());
            boolean correct = Arrays.equals(tempArr, solArr);
            good = good && correct;
            System.out.println("Test Case " + i + ": " + correct);
        }

        if (good)
        {
            System.out.println("\n\nEverything works!");
        } else {
            System.out.println("\n\nWork, everything does not >:(!");
        }
    }

    /**
     * Executes all the test cases by compiling and executing them and writing the output to stdout.
     *
     * @throws FileNotFoundException if file not found
     * @throws ScanErrorException    if scan error
     */
    private static void execAll() throws FileNotFoundException, ScanErrorException
    {
        int numCases = new File("src/parser/tests/compilerTests/cases").listFiles().length;
        String fileName = "src/parser/tests/compilerTests/cases/parserTest";

        for (int i = 0; i < numCases; i++)
        {
            System.out.println("-------Test Case " + i + "-------");
            Scanner s = new Scanner(new BufferedReader(new FileReader(fileName + i + ".txt")));

            Parser p = new Parser(s, "stdout");
            Program prog = p.parseProgram();
            try
            {
                prog.compile("src/parser/bytecode.asm");
                Runtime r = Runtime.getRuntime();
                Process process = r.exec("java -jar /Applications/Mars4_5.jar " +
                        "/Users/akulgoyal/Desktop/Compilers/Compiler/src/parser/bytecode.asm");
                BufferedReader inp = new BufferedReader(
                        new InputStreamReader(
                                process.getInputStream()));
                String line;
                while ((line = inp.readLine()) != null)
                {
                    System.out.println(line);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    /**
     * Clears the files by writing nothing to them
     *
     * @param fileName name of directory
     * @param numCases number of cases in the directory
     * @throws IOException if file not found
     */
    private static void clearFiles(String fileName, int numCases) throws IOException
    {
        for (int i = 0; i < numCases; i++)
        {
            new FileWriter(fileName + i + ".txt", false).close();
        }
    }
}

