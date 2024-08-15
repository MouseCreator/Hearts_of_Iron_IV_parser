package mouse.hoi.exception;

public class ScopeException extends RuntimeException{
    public ScopeException() {
        super();
    }

    public ScopeException(String message) {
        super(message);
    }

    public ScopeException(String message, Throwable cause) {
        super(message, cause);
    }
}
