package mouse.hoi.tools.parser.impl.dom.active;

import mouse.hoi.tools.parser.data.GameDate;
import mouse.hoi.tools.parser.impl.writer.dw.*;
import mouse.hoi.tools.parser.impl.writer.style.BooleanStyle;
import mouse.hoi.tools.parser.impl.writer.style.DoubleStyle;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;

public class SimpleActiveWriter implements ActiveWriter {
    private final DWSimple simple;
    @Override
    public DWData getDWData() {
        return simple;
    }

    private SimpleActiveWriter(DWSimple simple) {
        this.simple = simple;
    }

    public static SimpleActiveWriter string(String s) {
        return new SimpleActiveWriter(new DWString(s));
    }

    public static SimpleActiveWriter string(String s, StringStyle style) {
        return new SimpleActiveWriter(new DWString(s, style));
    }

    public static SimpleActiveWriter bool(boolean b) {
        return new SimpleActiveWriter(new DWBoolean(b));
    }

    public static SimpleActiveWriter bool(boolean b, BooleanStyle t) {
        return new SimpleActiveWriter(new DWBoolean(b, t));
    }

    public static SimpleActiveWriter integer(int i) {
        return new SimpleActiveWriter(new DWInteger(i));
    }

    public static SimpleActiveWriter dbl(double d) {
        return new SimpleActiveWriter(new DWDouble(d));
    }

    public static SimpleActiveWriter dbl(double d, DoubleStyle style) {
        return new SimpleActiveWriter(new DWDouble(d, style));
    }

    public static SimpleActiveWriter date(GameDate date) {
        return new SimpleActiveWriter(new DWDate(date));
    }
}
