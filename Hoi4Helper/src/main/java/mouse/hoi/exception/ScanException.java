package mouse.hoi.exception;

public class ScanException extends RuntimeException{
    public ScanException(String msg) {
        super(msg);
    }

    public ScanException(Exception e) {
        super(e);
    }

    public ScanException(String s, Exception e) {
        super(s, e);
    }
}
