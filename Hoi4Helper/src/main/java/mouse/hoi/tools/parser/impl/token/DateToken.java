package mouse.hoi.tools.parser.impl.token;

import lombok.Data;
import mouse.hoi.tools.parser.data.GameDate;

@Data
public class DateToken implements Token {
    private GameDate gameDate;
    private Location location;

    public DateToken(Location location, GameDate gameDate) {
        this.location = location;
        this.gameDate = gameDate;
    }

    @Override
    public Location location() {
        return location;
    }

    @Override
    public boolean is(String expected) {
        String format = gameDate.write();
        return format.equals(expected);
    }

    public GameDate value() {
        return gameDate;
    }
}
