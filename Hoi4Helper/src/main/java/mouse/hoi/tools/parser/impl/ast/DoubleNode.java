package mouse.hoi.tools.parser.impl.ast;

import lombok.Data;
import mouse.hoi.tools.parser.impl.token.Location;

@Data
public class DoubleNode implements SimpleNode {
    private final Location location;
    private final double value;

    @Override
    public String print() {
        return String.valueOf(value);
    }
}
