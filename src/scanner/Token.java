/**
 * Token class
 * A Token object represents a token in the input string. Stores the token and its type. Is
 * immutable. Override equals and hashCode methods.
 *
 * @author Akul Goyal
 * @version 4-12-2023
 */
package scanner;

/**
 * Class Token
 * A Token object represents a token in the input string. Stores the token and its type. Is
 * immutable. Override equals and hashCode methods.
 *
 * @author Akul Goyal
 * @version 9-4-2023
 */



public final class Token
{
    //instance variables
    private final String token;
    private final Scanner.TOKEN_TYPE type;

    /**
     * Constructor for objects of class Token. Sets the type and value of the Token.
     *
     * @param token the token
     * @param type  the type of the token
     */
    public Token(String token, Scanner.TOKEN_TYPE type)
    {
        this.token = token;
        this.type = type;
    }

    /**
     * Returns the type of the token.
     *
     * @return the type of the token
     */
    public Scanner.TOKEN_TYPE getType()
    {
        return type;
    }

    /**
     * Returns the token.
     *
     * @return the value of the token
     */
    public String getToken()
    {
        return token;
    }

    /**
     * Returns a string representation of the token.
     *
     * @return the type of the token followed by the value of the token in a String
     */
    public String toString()
    {
        return type + ": " + token;
    }

    /**
     * Returns true if the token is equal to the object passed in. Two Tokens are equal if they
     * have the same type and value.
     *
     * @param obj the object to compare to
     * @return true if the token is equal to the object passed in
     * @precondition obj is an instance of Token
     */
    public boolean equals(Object obj)
    {
        return type == ((Token) obj).getType() && token.equals(((Token) obj).getToken());
    }
}
