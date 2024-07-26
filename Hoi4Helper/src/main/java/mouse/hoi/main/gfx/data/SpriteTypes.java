package mouse.hoi.main.gfx.data;

import lombok.Data;
import mouse.hoi.tools.parser.impl.reader.Inits;

import java.util.ArrayList;
import java.util.List;
@Data
public class SpriteTypes implements Inits {

    private List<SpriteType> spriteTypeList;

    @Override
    public void initialize() {
        spriteTypeList = new ArrayList<>();
    }
}
