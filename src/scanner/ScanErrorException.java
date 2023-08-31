package scanner;
import java.lang.Exception;

public class ScanErrorException extends Exception
{
    public ScanErrorException (String message) {
        super(message);
    }
}
