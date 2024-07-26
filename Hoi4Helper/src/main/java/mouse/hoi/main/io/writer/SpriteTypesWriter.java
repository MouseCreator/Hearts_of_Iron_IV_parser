package mouse.hoi.main.io.writer;

import mouse.hoi.main.gfx.data.SpriteTypes;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import org.springframework.stereotype.Service;

@Service
public class SpriteTypesWriter implements DataWriter<SpriteTypes> {
    @Override
    public Class<SpriteTypes> forType() {
        return SpriteTypes.class;
    }

    @Override
    public void write(SpecialWriter writer, SpriteTypes object) {
        writer.list().block("SpriteType", object::getSpriteTypeList);
    }
}
