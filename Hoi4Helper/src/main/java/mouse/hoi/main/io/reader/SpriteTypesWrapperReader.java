package mouse.hoi.main.io.reader;

import mouse.hoi.main.gfx.data.SpriteTypes;
import mouse.hoi.main.gfx.data.SpriteTypesWrapper;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
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
