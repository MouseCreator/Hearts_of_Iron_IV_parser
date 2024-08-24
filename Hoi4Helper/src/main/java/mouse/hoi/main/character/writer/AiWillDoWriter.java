package mouse.hoi.main.character.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.AiWillDo;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiWillDoWriter implements DataWriter<AiWillDo> {

    private final WriterSupport writerSupport;
    @Override
    public Class<AiWillDo> forType() {
        return AiWillDo.class;
    }

    @Override
    public DWData write(AiWillDo object, ObjectStyle style) {
        DWObjectBuilder build = writerSupport.build(style);
        build.key("factor").dbl(object::getFactor);
        build.listAll("modifier").objects(object::getModifierList);
        return build.get();
    }
}
