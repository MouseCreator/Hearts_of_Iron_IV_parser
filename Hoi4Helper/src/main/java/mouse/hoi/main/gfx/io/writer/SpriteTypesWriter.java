package mouse.hoi.main.gfx.io.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.data.SpriteTypes;
import mouse.hoi.tools.parser.impl.writer.n.DataWriter;
import mouse.hoi.tools.parser.impl.writer.n.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.n.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.n.support.WriterSupport;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpriteTypesWriter implements DataWriter<SpriteTypes> {

    private final WriterSupport support;
    @Override
    public Class<SpriteTypes> forType() {
        return SpriteTypes.class;
    }

    @Override
    public DWData write(SpriteTypes object, ObjectStyle style) {
        DWObjectBuilder builder = support.build(style);
        builder.listAll("spriteType").objects(object::getSpriteTypeList);
        return builder.get();
    }
}
