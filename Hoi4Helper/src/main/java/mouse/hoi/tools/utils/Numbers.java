package mouse.hoi.tools.utils;

public class Numbers {
    public static boolean dEquals(double d1, double d2, double th) {
        return Math.abs(d1 - d2) < th;
    }
    public static boolean dEquals(double d1, double d2) {
        return dEquals(d1, d2, 0.000001);
    }

    public static boolean dWhole(double d) {
        return dEquals(d, Math.round(d));
    }

    public static boolean dWhole(double d, double  th) {
        return dEquals(d, Math.round(d), th);
    }
}
