package mouse.hoi.tools.parser.impl.reader.lr;


import lombok.Data;
import lombok.EqualsAndHashCode;
import mouse.hoi.tools.parser.data.GameDate;
@Data
@EqualsAndHashCode(callSuper = true)
public class DateValue extends AbstractPossibleValue{
    private final GameDate gameDate;

    public DateValue(GameDate gameDate) {
        this.gameDate = gameDate;
    }

    @Override
    public String stringValue() {
        return gameDate.write();
    }

    @Override
    public boolean isDate() {
        return true;
    }

    @Override
    public GameDate dateValue() {
        return gameDate;
    }
}
