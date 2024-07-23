package mouse.hoi.tools.parser.impl.ast;

import lombok.Data;
import mouse.hoi.tools.parser.impl.token.Location;
@Data
public class IntegerNode implements SimpleNode{
    private final Location location;
    private final int value;

    public IntegerNode(Location location, int value) {
        this.location = location;
        this.value = value;
    }

    @Override
    public String print() {
        return String.valueOf(value);
    }
}
