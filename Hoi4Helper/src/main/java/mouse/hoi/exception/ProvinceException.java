package mouse.hoi.exception;

public class ProvinceException extends RuntimeException{
    public ProvinceException() {
    }

    public ProvinceException(String message) {
        super(message);
    }

    public ProvinceException(String message, Throwable cause) {
        super(message, cause);
    }
}
