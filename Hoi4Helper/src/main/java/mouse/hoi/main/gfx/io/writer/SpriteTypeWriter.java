package mouse.hoi.main.gfx.io.writer;

import mouse.hoi.main.gfx.data.SpriteType;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.utils.Types;
import org.springframework.stereotype.Service;

@Service
public class SpriteTypeWriter implements DataWriter<SpriteType> {
    @Override
    public Class<SpriteType> forType() {
        return SpriteType.class;
    }

    @Override
    public void write(SpecialWriter writer, SpriteType object) {
        writer.key("name").value(object::getName, StringStyle.QUOTED).ln()
                .key("texturefile").value(object::getTextureFile, StringStyle.QUOTED).ln()
                .key("effectFile").testValue(object::getEffectFile).printIfNotNullLn(StringStyle.QUOTED)
                .list().testNotEmpty(object::getAnimationList).block("animation")
                .key("legacy_lazy_load").testValue(object::isLegacyLazyLoad).printIfLn("no", Types::mapBoolean);
    }
}
