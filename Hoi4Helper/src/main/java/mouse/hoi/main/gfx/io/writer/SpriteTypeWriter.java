package mouse.hoi.main.gfx.io.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.data.SpriteType;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.utils.NotNull;
import mouse.hoi.tools.utils.TestIf;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpriteTypeWriter implements DataWriter<SpriteType> {

    private final WriterSupport support;
    @Override
    public Class<SpriteType> forType() {
        return SpriteType.class;
    }

    @Override
    public DWData write(SpriteType object, ObjectStyle style) {
        DWObjectBuilder b = support.build(style);
        b.key("name").string(object::getName, StringStyle.QUOTED);
        b.key("texturefile").string(object::getTextureFile, StringStyle.QUOTED);

        NotNull.supply(object::getEffectFile, s -> b.key("effectFile").string(s,StringStyle.QUOTED));

        b.listAll("animation").objects(object::getAnimationList);

        TestIf.ifNot(object::isLegacyLazyLoad).then(s -> b.key("legacy_lazy_load").bool(s));

        return b.get();
    }
}
