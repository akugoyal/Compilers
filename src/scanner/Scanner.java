package scanner;
import java.io.*;

/**
 * A new line would mean that the keyword is "if". An open parentheses would mean that the
 * conditional of the if statement will follow.
 *
 *
 * @author Akul Goyal
 */
public class Scanner
{
    private boolean eof;
    private Reader in;
    private char currentChar;

    public static enum TOKEN_TYPE
    {
        IDENTIFIER,

        NUMBER,

        OPERAND,

        END_OF_FILE,

        END_OF_LINE
    }

    public Scanner(Reader in)
    {
        this.in = in;
        eof = false;
        getNextChar();
    }

    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if (inp == -1)
                eof = true;
            else
                currentChar = (char) inp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public boolean hasNextToken()
    {
        return !eof;
    }


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
            //put diff stuff
            //throw new IllegalArgumentException("currentChar: " + currentChar + " does not match + c);
        }
    }

    /**
     * The isWhiteSpace method checks to see if the input is a white space character.
     *
     * @param s is the character to be checked
     * @return true if the input is a white space character, false otherwise.
     * @precondition s.length() == 1
     */
    private boolean isWhiteSpace(char s)
    {
        return String.valueOf(s).matches("[‘ ‘ ‘\t’ ‘\r’ ‘\n’]");
    }

    private boolean isLetter(char s) {
        return String.valueOf(s).toLowerCase().matches("[a-z A-Z]");
    }

    private boolean isDigit(char s)
    {
        return String.valueOf(s).matches("[0-9]");
    }

    private boolean isEndOfFile(char s)
    {
        return s == '.';
    }

    private Token scanNumber() throws ScanErrorException
    {
        if (!isDigit(currentChar)) {
            throw new ScanErrorException("Found a non-digit character");
        }
        String token = "";
        while (hasNextToken() && isDigit(currentChar))
        {
            token += currentChar;
            eat(currentChar);
        }
        return new Token(token, TOKEN_TYPE.NUMBER);
    }

    /**public Token nextToken() throws ScanErrorException
    {
        while (hasNextToken() && isWhiteSpace(currentChar))
        {
            eat(currentChar);
        }
        //END_OF_FILE
        if (!hasNextToken() || isEndOfFile(currentChar))
        {
            //System.out.println("End of file reached");
            return new Token("END", TOKEN_TYPE.END_OF_FILE);
        }

        //End of line
        if (currentChar == ';') {
            return new Token(";", TOKEN_TYPE.END_OF_LINE);
        }

        //Number
        if (isDigit(currentChar))
        {
            scanNumber();
        }

        //Identifier
        if (currentChar >= ) {

        }
        //END_OF_PHRASE
        if (isPhraseTerminator(currentChar))
        {
            //System.out.println("End of phrase reached");
            Token token = new Token(currentChar, TOKEN_TYPE.END_OF_PHRASE);
            eat(currentChar);
            return token;
        }

        //WORD
        if (isLetter(currentChar) || isDigit(currentChar) || isSpecialCharacter(currentChar))
        {
            String token = "";
            while (hasNextToken() && isLetter(currentChar) || isDigit(currentChar) ||
                    isSpecialCharacter(currentChar) || currentChar.equals("'"))
            {
                token += currentChar.toLowerCase();
                eat(currentChar);
            }

            //DIGIT
            if (token.length() == 1 && isDigit(token))
            {
                return new Token(token, TOKEN_TYPE.DIGIT);
            }
            return new Token(token, TOKEN_TYPE.WORD);
        }

        //UNKNOWN
        String token = "" + currentChar;
        eat(currentChar);
        return new Token(token, TOKEN_TYPE.UNKNOWN);
    }**/

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner s = new Scanner(new FileReader("test.txt"));
        System.out.println(s.isLetter('a'));
    }
}
