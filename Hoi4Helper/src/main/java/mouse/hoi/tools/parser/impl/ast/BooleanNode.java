package mouse.hoi.tools.parser.impl.ast;

import lombok.Data;
import mouse.hoi.tools.parser.impl.token.Location;
import mouse.hoi.tools.utils.Types;
@Data
public class BooleanNode implements SimpleNode{
    public BooleanNode(Location location, boolean value) {
        this.value = value;
        this.location = location;
    }
    private Location location;
    private boolean value;
    @Override
    public String print() {
        return Types.mapBoolean(value);
    }
}
