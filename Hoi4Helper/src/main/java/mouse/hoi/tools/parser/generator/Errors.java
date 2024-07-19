package mouse.hoi.tools.parser.generator;

import mouse.hoi.tools.parser.impl.error.ScanException;

public class Errors {
    public static void report(String message, int line, int position) {
        String toReport = line + ":" + position + ": " + message;
        throw new ScanException(toReport);
    }
}
