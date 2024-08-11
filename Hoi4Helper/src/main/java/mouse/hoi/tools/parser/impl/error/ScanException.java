package mouse.hoi.tools.parser.impl.error;

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
