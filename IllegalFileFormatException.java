/**
 * @author tortoiselala
 */
public class IllegalFileFormatException extends Exception {
    public IllegalFileFormatException() {
    }

    public IllegalFileFormatException(String message) {
        super(message);
    }

    public IllegalFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalFileFormatException(Throwable cause) {
        super(cause);
    }

    public IllegalFileFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
