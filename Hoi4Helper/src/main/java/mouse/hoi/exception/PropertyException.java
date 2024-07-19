package mouse.hoi.exception;

public class PropertyException extends RuntimeException{
    public PropertyException(String message) {
        super(message);
    }

    public PropertyException(Throwable cause) {
        super(cause);
    }
}
