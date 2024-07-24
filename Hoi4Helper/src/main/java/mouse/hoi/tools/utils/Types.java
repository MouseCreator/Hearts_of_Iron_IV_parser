package mouse.hoi.tools.utils;

public class Types
{
    private Types() {
    }

    public static String mapBoolean(boolean b) {
        return b ? "yes" : "no";
    }
    public static boolean mapBoolean(String s) {
        String t = s.toLowerCase();
        if ("yes".equals(t)) {
            return true;
        }
        if ("no".equals(t)) {
            return false;
        }
        throw new RuntimeException("Unable to map to boolean: " + s);
    }
}
