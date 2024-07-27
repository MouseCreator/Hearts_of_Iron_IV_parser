package mouse.hoi.exception;

public class PossibleValueException extends RuntimeException{
    public PossibleValueException() {
    }

    public PossibleValueException(String message) {
        super(message);
    }

    public PossibleValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
