package mouse.hoi.main.gfx.data;

import lombok.Data;
import mouse.hoi.tools.parser.annotation.GameObj;
import mouse.hoi.tools.parser.annotation.WriteAs;
import mouse.hoi.tools.parser.impl.interpreter.simple.Inits;

import java.util.ArrayList;
import java.util.List;
@Data
@GameObj
public class SpriteTypes implements Inits {
    @WriteAs("SpriteType")
    private List<SpriteType> spriteTypeList;

    @Override
    public void initialize() {
        spriteTypeList = new ArrayList<>();
    }
}
