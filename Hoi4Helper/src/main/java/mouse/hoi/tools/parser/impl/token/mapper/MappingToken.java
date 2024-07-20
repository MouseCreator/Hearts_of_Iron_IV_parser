package mouse.hoi.tools.parser.impl.token.mapper;

import lombok.Data;
import mouse.hoi.tools.parser.impl.token.Location;

@Data
public class MappingToken {
    private String value;
    private Location location;
}
