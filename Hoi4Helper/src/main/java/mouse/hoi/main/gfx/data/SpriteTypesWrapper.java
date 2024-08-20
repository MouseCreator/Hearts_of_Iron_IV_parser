package mouse.hoi.main.gfx.data;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class SpriteTypesWrapper {

    private List<SpriteTypes> spriteTypes;

    public SpriteTypesWrapper() {
        this.spriteTypes = new ArrayList<>();
    }

    public SpriteTypes merged() {
        List<SpriteType> result = new ArrayList<>();
        for (SpriteTypes types : spriteTypes) {
            List<SpriteType> spriteTypeList = types.getSpriteTypeList();
            result.addAll(spriteTypeList);
        }
        SpriteTypes spriteTypes = new SpriteTypes();
        spriteTypes.getSpriteTypeList().addAll(result);
        return spriteTypes;
    }
}
