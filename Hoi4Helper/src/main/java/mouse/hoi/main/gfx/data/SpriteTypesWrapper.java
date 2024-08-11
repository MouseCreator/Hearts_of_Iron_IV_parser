package mouse.hoi.main.gfx.data;


import java.util.ArrayList;
import java.util.List;

public class SpriteTypesWrapper extends AbstractWrapper<SpriteTypes> {
    public SpriteTypes merged() {
        List<SpriteTypes> list = getList();
        List<SpriteType> result = new ArrayList<>();
        for (SpriteTypes types : list) {
            List<SpriteType> spriteTypeList = types.getSpriteTypeList();
            result.addAll(spriteTypeList);
        }
        SpriteTypes spriteTypes = new SpriteTypes();
        spriteTypes.setSpriteTypeList(result);
        return spriteTypes;
    }
}
