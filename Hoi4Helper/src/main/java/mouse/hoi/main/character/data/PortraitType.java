package mouse.hoi.main.character.data;

import lombok.Data;
import mouse.hoi.tools.parser.impl.dom.DomSimple;

@Data
public class PortraitType {
    private String name;
    private String large;
    private String small;

    public PortraitType(String key) {
        this.name = key;
    }
}
