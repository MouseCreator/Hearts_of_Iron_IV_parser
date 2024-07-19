package mouse.hoi.main.gfx.data;

import lombok.Data;
import mouse.hoi.tools.parser.annotation.WriteAs;

@Data
public class SpriteType {
    @WriteAs
    private String name;
    @WriteAs("texturefile")
    private String path;
}
