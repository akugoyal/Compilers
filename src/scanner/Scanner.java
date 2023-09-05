package scanner;

import java.io.*;

/**
 * A new line would mean that the keyword is "if". An open parentheses would mean that the
 * conditional of the if statement will follow. New line is skipped over, parentheses is returned
 * in the nextToken method.
 * <p>
 * The character you pass to eat will be the current character which you want to move on from.
 * This lookahead helps in being able to determine what the next token is, which then affects
 * whether you have detected one complete token or are still in the process of parsing it.
 * <p>
 * Class Scanner
 * The Scanner class parses an input file and tokenizes the input. It is responsible for
 * identifying the type of token and returning the token and its type. It is also responsible for
 * throwing a ScanErrorException if the input is not valid. Tokens are Identifiers, Numbers,
 * Operands, End of Files, and End of Lines.
 *
 * @author Akul Goyal
 * @version 9-4-2023
 */
public class Scanner
{
    //instance variables
    private boolean eof;
    private BufferedReader in;
    private char currentChar;

    /**
     * Constructor for objects of class Scanner. Sets the Reader instance variable and calls the
     * eat method to set the currentChar instance variable.
     *
     * @param in the Reader object
     */
    public Scanner(BufferedReader in)
    {
        this.in = in;
        eof = false;
        getNextChar();
    }

    public static boolean isEndOfFile(char s)
    {
        return s == '.';
    }

    /**
     * The isWhiteSpace method checks to see if the input is a white space character.
     *
     * @param s is the character to be checked
     * @return true if the input is a white space character, false otherwise.
     * @precondition s.length() == 1
     */
    public static boolean isWhiteSpace(char s)
    {
        return String.valueOf(s).matches("[‘ ‘ ‘\t’ ‘\r’ ‘\n’]");
    }

    /**
     * The isLetter method checks to see if the input is a letter.
     *
     * @param s is the character to be checked
     * @return true if the input is a letter, false otherwise
     */
    public static boolean isLetter(char s)
    {
        return String.valueOf(s).toLowerCase().matches("[a-zA-Z]");
    }

    /**
     * The isDigit method checks to see if the input is a digit.
     *
     * @param s is the character to be checked
     * @return true if the input is a digit, false otherwise
     */
    public static boolean isDigit(char s)
    {
        return String.valueOf(s).matches("[0-9]");
    }

    /**
     * The isOperand method checks to see if the input is an operand.
     *
     * @param s is the character to be checked
     * @return true if the input is an operand, false otherwise
     */
    public static boolean isOperand(char s)
    {
        return String.valueOf(s).matches("[-=+*/%():<>]");
    }

    /**
     * The getNextChar method reads the next character from the input stream and converts it to a
     * char type. If the end of the stream is reached, the eof flag is set to true.
     */
    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if (inp == -1) eof = true;
            else currentChar = (char) inp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * The eat method checks to see if the current character matches the input. If it does, it
     * gets the next character and returns true.
     *
     * @param c is the character to be matched
     * @return true if the current character matches the input.
     * @throws ScanErrorException if the current character does not match the input
     */
    private boolean eat(char c) throws ScanErrorException
    {
        if (currentChar == c)
        {
            getNextChar();
            return true;
        }
        else
        {
            throw new ScanErrorException("Illegal character - expected" + currentChar + " and " +
                    "found " + c);
        }
    }

    /**
     * The hasNextToken method checks to see if the end of the file has been reached.
     *
     * @return true if the end of the file has not been reached, false otherwise.
     */
    public boolean hasNextToken()
    {
        return !eof;
    }

    /**
     * The scanNumber method scans the input and returns a number token.
     *
     * @return a number token
     * @throws ScanErrorException if the input is not a digit
     */
    private Token scanNumber() throws ScanErrorException
    {
        if (!isDigit(currentChar))
        {
            throw new ScanErrorException("Found a non-digit character: " + currentChar);
        }
        String token = "";
        while (hasNextToken() && isDigit(currentChar))
        {
            token += currentChar;
            eat(currentChar);
        }
        return new Token(token, TOKEN_TYPE.NUMBER);
    }

    /**
     * The scanIdentifier method scans the input and returns an identifier token.
     *
     * @return an identifier token
     * @throws ScanErrorException if the input is not a letter
     */
    private Token scanIdentifier() throws ScanErrorException
    {
        if (!isLetter(currentChar))
        {
            throw new ScanErrorException("Found a non-letter and non-digit character: " +
                    currentChar);
        }
        String token = "";
        while (hasNextToken() && (isLetter(currentChar) || isDigit(currentChar)))
        {
            token += currentChar;
            eat(currentChar);
        }
        return new Token(token, TOKEN_TYPE.IDENTIFIER);
    }

    /**
     * The scanOperand method scans the input and returns an operand token.
     *
     * @return an operand token
     * @throws ScanErrorException if the input is not an operand
     */
    private Token scanOperand() throws ScanErrorException
    {
        if (!isOperand(currentChar))
        {
            throw new ScanErrorException("Illegal character found: " + currentChar);
        }
        String token = "";
        while (hasNextToken() && isOperand(currentChar))
        {
            token += currentChar;
            eat(currentChar);
        }
        return new Token(token, TOKEN_TYPE.OPERAND);
    }

    /**
     * The nextToken method returns the next token in the input stream, utilizing the scanNumber,
     * scanIdentifier, and scanOperand methods to tokenize the input.
     *
     * @return the next token in the input stream
     * @throws ScanErrorException if the input is not a recognized character
     */
    public Token nextToken() throws ScanErrorException
    {
        String unknown = "";
        try
        {
            while (hasNextToken() && isWhiteSpace(currentChar))
            {
                eat(currentChar);
            }
            //END_OF_FILE
            if (!hasNextToken() || isEndOfFile(currentChar))
            {
                eat(currentChar);
                eof = true;
                //System.out.println("End of file reached");
                return new Token("END", TOKEN_TYPE.END_OF_FILE);
            }

            //End of line
            if (currentChar == ';')
            {
                eat(currentChar);
                return new Token(";", TOKEN_TYPE.END_OF_LINE);
            }

            //Number
            if (isDigit(currentChar))
            {
                return scanNumber();
            }

            //Identifier
            if (isLetter(currentChar))
            {
                return scanIdentifier();
            }

            //Operand
            if (isOperand(currentChar))
            {
                Token op = scanOperand();
                if (op.getToken().length() >= 2 && op.getToken().substring(0, 2).equals("//"))
                {
                    while (currentChar != '\n' && currentChar != '\r')
                    {
                        eat(currentChar);
                    }
                    nextToken();
                }
                else
                {
                    return op;
                }
            }
            else
            {
                throw new ScanErrorException("Illegal character found when parsing: " +
                        currentChar);
            }
        }
        catch (ScanErrorException s)
        {
            s.printStackTrace();
            //unknown = String.valueOf(currentChar);
            //eat(currentChar);
            System.out.println("UNKOWN: " + currentChar);
            System.exit(1);
        }

        return null;
        //return new Token(unknown, TOKEN_TYPE.UNKNOWN);
    }

    /**
     * Define symbolic constants for each type of token.
     */
    public static enum TOKEN_TYPE
    {
        /**
         * An identifier is defined as a non-empty sequence of characters that begins with an
         * alpha and then consists of alpha characters and digits.
         */
        IDENTIFIER,

        /**
         * A number is defined as a non-empty sequence of digits.
         */
        NUMBER,

        /**
         * An operand is defined as a non-empty sequence of characters that are valid operands:
         * =, +, -, *, /, %, (, ), :, <, >.
         */
        OPERAND,

        /**
         * An end-of-file token which is returned when the scanner is asked for a token and the
         * end of the file has been reached by either reaching the end of the file or by
         * encountering a period.
         */
        END_OF_FILE,

        /**
         * An end-of-line token which is returned when the scanner is asked for a token and the
         * scanner encounters a semicolon.
         */
        END_OF_LINE,

        /**
         * An unknown token which is returned when the scanner is asked for a token and the
         * scanner encounters a character that is not recognized.
         */
        UNKNOWN
    }
}
