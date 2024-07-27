package mouse.hoi.exception;

public class UnsupportedReaderException extends RuntimeException{
    public UnsupportedReaderException() {
    }

    public UnsupportedReaderException(String message) {
        super(message);
    }

    public UnsupportedReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
