package mouse.hoi.exception;

public class InterpretationException extends RuntimeException{
    public InterpretationException(String message) {
        super(message);
    }

    public InterpretationException(Exception e) {
        super(e);
    }

    public InterpretationException(String s, Exception e) {
        super(s, e);
    }
}
