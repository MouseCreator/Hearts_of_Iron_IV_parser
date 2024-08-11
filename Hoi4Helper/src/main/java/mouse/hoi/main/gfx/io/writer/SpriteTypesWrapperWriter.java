package mouse.hoi.main.gfx.io.writer;

import mouse.hoi.main.gfx.data.SpriteTypesWrapper;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import org.springframework.stereotype.Service;

@Service
public class SpriteTypesWrapperWriter implements DataWriter<SpriteTypesWrapper> {
    @Override
    public Class<SpriteTypesWrapper> forType() {
        return SpriteTypesWrapper.class;
    }

    @Override
    public void write(SpecialWriter writer, SpriteTypesWrapper object) {
        writer.list().block("spriteTypes", object::getList).ln();
    }
}
