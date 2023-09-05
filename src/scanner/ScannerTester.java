package scanner;

import java.io.*;

/**
 * Class ScannerTester
 * The ScannerTester class tests the Scanner class by reading in a file and printing out the tokens
 * and their types.
 *
 * @author Akul Goyal
 * @version 9-4-2023
 */
public class ScannerTester
{
    /**
     * Main method for ScannerTester
     *
     * @param args the command line arguments
     * @throws FileNotFoundException if the file to be read is not found
     * @throws ScanErrorException    if the Scanner encounters an error
     */
    public static void main(String[] args) throws FileNotFoundException, ScanErrorException
    {
        Scanner s =
                new Scanner(new BufferedReader(new FileReader("src/scanner/scannerTestAdvanced" +
                        ".txt")));
        while (s.hasNextToken())
        {
            System.out.println(s.nextToken());
        }
        System.out.println(s.nextToken());
    }
}
