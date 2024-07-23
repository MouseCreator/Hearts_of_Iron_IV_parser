package mouse.hoi.exception;

public class ParsingException extends RuntimeException{
    public ParsingException() {
    }

    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(Throwable cause) {
        super(cause);
    }
}
