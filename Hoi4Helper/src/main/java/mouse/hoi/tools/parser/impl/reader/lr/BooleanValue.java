package mouse.hoi.tools.parser.impl.reader.lr;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mouse.hoi.tools.utils.Types;
@EqualsAndHashCode(callSuper = true)
@Data
public class BooleanValue extends AbstractPossibleValue{

    private final boolean value;

    @Override
    public boolean isBoolean() {
        return true;
    }

    @Override
    public boolean boolValue() {
        return value;
    }

    @Override
    public String stringValue() {
        return Types.mapBoolean(value);
    }
}
