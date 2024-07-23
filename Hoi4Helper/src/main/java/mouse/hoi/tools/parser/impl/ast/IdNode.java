package mouse.hoi.tools.parser.impl.ast;

import lombok.Data;
import mouse.hoi.tools.parser.impl.token.Location;

@Data
public class IdNode implements SimpleNode{
    private final Location location;
    private final String id;

    @Override
    public String print() {
        return id;
    }
}
