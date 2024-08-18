package mouse.hoi.main.gfx.io.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.gfx.data.SpriteTypesWrapper;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpriteTypesWrapperWriter implements DataWriter<SpriteTypesWrapper> {

    private final WriterSupport writerSupport;
    @Override
    public Class<SpriteTypesWrapper> forType() {
        return SpriteTypesWrapper.class;
    }
    @Override
    public DWData write(SpriteTypesWrapper wrapper, ObjectStyle style) {
        DWObjectBuilder builder = writerSupport.build(style);
        builder.listAll("spriteTypes").objects(wrapper::getList);
        return builder.get();
    }
}
