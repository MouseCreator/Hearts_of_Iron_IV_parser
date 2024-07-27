package mouse.hoi.tools.parser.impl.reader.lr;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mouse.hoi.tools.parser.impl.reader.subs.SubscriptObject;
@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptObjectValue extends AbstractPossibleValue {
    private final SubscriptObject subscriptObject;
    @Override
    public boolean isSubscript() {
        return true;
    }

    @Override
    public SubscriptObject subscriptValue() {
        return subscriptObject;
    }

    @Override
    public String stringValue() {
        return subscriptObject.print();
    }
}
