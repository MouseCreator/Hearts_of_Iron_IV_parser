package mouse.hoi.exception;

public class EffectException extends RuntimeException{
    public EffectException() {
    }

    public EffectException(String message) {
        super(message);
    }

    public EffectException(String message, Throwable cause) {
        super(message, cause);
    }
}
