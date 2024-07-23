package mouse.hoi.tools.parser.impl.ast;

import lombok.Getter;
import mouse.hoi.tools.parser.impl.token.Location;
@Getter
public class StringNode implements SimpleNode {
    private final Location location;
    private final String value;
    public StringNode(Location location, String value) {
        this.location = location;
        this.value = value;
    }

    @Override
    public String print() {
        return "\"" + value + "\"";
    }
}
