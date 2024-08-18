package mouse.hoi.tools.parser.impl.dom.query;

import mouse.hoi.tools.parser.data.GameDate;
import mouse.hoi.tools.parser.impl.reader.lr.*;
import mouse.hoi.tools.parser.impl.reader.subs.SubscriptObject;

public class SV {
    public static StringValue string(String string) {
        return new StringValue(string);
    }
    public static IntegerValue integer(int i) {
        return new IntegerValue(i);
    }
    public static DoubleValue doublev(double d) {
        return new DoubleValue(d);
    }
    public static BooleanValue bool(boolean b) {
        return new BooleanValue(b);
    }
    public static DateValue date(GameDate gameDate) {
        return new DateValue(gameDate);
    }
    public static SubscriptObjectValue subs(SubscriptObject subscriptObject) {
        return new SubscriptObjectValue(subscriptObject);
    }
}
