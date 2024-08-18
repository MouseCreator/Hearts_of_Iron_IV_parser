package mouse.hoi.tools.parser.generator;

import mouse.hoi.exception.ScanException;

public class Errors {
    public static void report(String message, int line, int position) {
        String toReport = line + ":" + position + ": " + message;
        throw new ScanException(toReport);
    }
}
