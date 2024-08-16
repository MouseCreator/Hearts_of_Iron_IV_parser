package mouse.hoi.exception;

public class DomException extends RuntimeException{
    public DomException() {
    }

    public DomException(String message) {
        super(message);
    }

    public DomException(String message, Throwable cause) {
        super(message, cause);
    }
}
