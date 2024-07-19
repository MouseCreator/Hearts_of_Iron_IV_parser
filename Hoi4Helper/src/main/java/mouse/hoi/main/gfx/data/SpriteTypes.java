package mouse.hoi.main.gfx.data;

import lombok.Data;
import mouse.hoi.tools.parser.annotation.WriteAs;

import java.util.List;
@WriteAs("spriteTypes")
@Data
public class SpriteTypes {
    @WriteAs("SpriteType")
    private List<SpriteType> spriteTypeList;
}
