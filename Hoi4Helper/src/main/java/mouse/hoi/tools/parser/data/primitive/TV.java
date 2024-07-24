package mouse.hoi.tools.parser.data.primitive;

public class TV {
    public static TBoolean tBool(boolean v) {
        return new RegularBoolean(v);
    }
    public static TDouble tDouble(double d) {
        return new RegularDouble(d);
    }
    public static TInt tInt(int i) {
        return new RegularInt(i);
    }

    public static TString tString(String s) {
        return new RegularString(s);
    }
}
