package mouse.hoi.tools.parser.impl.reader.lr;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StringValue extends AbstractPossibleValue{

    private final String value;
    @Override
    public String stringValue() {
        return value;
    }
}
