package mouse.hoi.tools.parser.impl.reader.lr;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mouse.hoi.tools.parser.data.GameDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class DoubleValue extends AbstractPossibleValue {
    private final double value;

    public DoubleValue(double value) {
        this.value = value;
    }

    @Override
    public String stringValue() {
        return String.valueOf(value);
    }

    @Override
    public boolean isDouble() {
        return true;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    public boolean isDate() { return true; }

    @Override
    public GameDate dateValue() {
        int year = (int) value;
        int month = (int) (value * 100) % 100;
        return new GameDate(year, month);
    }
}
