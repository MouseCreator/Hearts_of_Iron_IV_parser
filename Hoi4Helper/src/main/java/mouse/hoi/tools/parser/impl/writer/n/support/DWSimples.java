package mouse.hoi.tools.parser.impl.writer.n.support;

import mouse.hoi.tools.parser.data.GameDate;
import mouse.hoi.tools.parser.impl.writer.n.dw.*;
import mouse.hoi.tools.parser.impl.writer.style.BooleanStyle;
import mouse.hoi.tools.parser.impl.writer.style.DoubleStyle;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;

import java.util.function.Supplier;

public class DWSimples {
    public DWString string(Supplier<String> s) {
        return new DWString(s.get());
    }
    public DWSimple string(String s) {
        return new DWString(s);
    }
    public DWString string(Supplier<String> s, StringStyle style) {
        return new DWString(s.get(), style);
    }
    public DWSimple string(String s, StringStyle style) {
        return new DWString(s, style);
    }

    public DWInteger integer(Supplier<Integer> i) {
        return integer(i.get());
    }
    public DWInteger integer(int i) {
        return new DWInteger(i);
    }

    public DWDouble dbl(Supplier<Double> d) {
        return new DWDouble(d.get());
    }
    public DWDouble dbl(double d) {
        return new DWDouble(d);
    }
    public DWDouble dbl(Supplier<Double> d, DoubleStyle style) {
        return new DWDouble(d.get(), style);
    }
    public DWDouble dbl(double d, DoubleStyle style) {
        return new DWDouble(d, style);
    }
    public DWBoolean bool(Supplier<Boolean> b) {
        return new DWBoolean(b.get());
    }
    public DWBoolean bool(boolean b) {
        return new DWBoolean(b);
    }
    public DWBoolean bool(Supplier<Boolean> b, BooleanStyle style) {
        return new DWBoolean(b.get(), style);
    }
    public DWBoolean bool(boolean b, BooleanStyle style) {
        return new DWBoolean(b, style);
    }
    public DWDate date(GameDate gameDate) {
        return new DWDate(gameDate);
    }
    public DWDate date(Supplier<GameDate> supplier) {
        return new DWDate(supplier.get());
    }
}
