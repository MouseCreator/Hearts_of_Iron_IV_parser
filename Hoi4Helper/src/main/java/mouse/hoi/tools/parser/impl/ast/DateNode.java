package mouse.hoi.tools.parser.impl.ast;

import lombok.Data;
import mouse.hoi.tools.parser.data.GameDate;
import mouse.hoi.tools.parser.impl.token.Location;
@Data
public class DateNode implements SimpleNode{
    private final GameDate gameDate;
    private final Location location;

    public DateNode(Location location, GameDate gameDate) {
        this.gameDate = gameDate;
        this.location = location;
    }

    @Override
    public String print() {
        return null;
    }
}
