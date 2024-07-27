package mouse.hoi.tools.parser.impl.reader.lr;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class IntegerValue extends AbstractPossibleValue {
    private final int value;

    @Override
    public boolean isInteger() {
        return true;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public String stringValue() {
        return String.valueOf(value);
    }
}
