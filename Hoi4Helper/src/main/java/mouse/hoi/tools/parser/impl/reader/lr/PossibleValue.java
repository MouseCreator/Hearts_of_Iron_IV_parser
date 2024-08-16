package mouse.hoi.tools.parser.impl.reader.lr;

import mouse.hoi.tools.parser.data.GameDate;
import mouse.hoi.tools.parser.impl.reader.subs.SubscriptObject;

public interface PossibleValue {
    boolean isSubscript();
    boolean isDouble();
    boolean isInteger();
    boolean isBoolean();
    SubscriptObject subscriptValue();
    int intValue();
    boolean boolValue();
    double doubleValue();
    String stringValue();
    boolean isDate();
    GameDate dateValue();
}
