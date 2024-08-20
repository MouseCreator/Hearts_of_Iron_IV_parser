package mouse.hoi.main.gfx.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class SpriteTypes {

    private List<SpriteType> spriteTypeList;

    public SpriteTypes() {
        spriteTypeList = new ArrayList<>();
    }
}
