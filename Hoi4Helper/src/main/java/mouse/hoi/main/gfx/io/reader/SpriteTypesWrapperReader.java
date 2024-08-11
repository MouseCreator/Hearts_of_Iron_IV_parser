package mouse.hoi.main.gfx.io.reader;

import mouse.hoi.main.gfx.data.SpriteTypes;
import mouse.hoi.main.gfx.data.SpriteTypesWrapper;
import org.springframework.stereotype.Service;

@Service
public class SpriteTypesWrapperReader extends WrapperReader<SpriteTypesWrapper, SpriteTypes> {

    @Override
    public String token() {
        return "spriteTypes";
    }
    @Override
    public Class<SpriteTypes> wraps() {
        return SpriteTypes.class;
    }

    @Override
    public Class<SpriteTypesWrapper> forType() {
        return SpriteTypesWrapper.class;
    }
}
