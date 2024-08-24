package mouse.hoi.main.character.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.AiWillDoModifier;
import mouse.hoi.main.character.data.AiWillDoOperator;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiWillDoModifierWriter implements DataWriter<AiWillDoModifier> {

    private final WriterSupport writerSupport;
    @Override
    public Class<AiWillDoModifier> forType() {
        return AiWillDoModifier.class;
    }

    @Override
    public DWData write(AiWillDoModifier object, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        for (AiWillDoOperator operator : object.getOperators()) {
            b.key(operator.getOperation()).dbl(operator::getValue);
        }
        b.embedded(object::getConditions);
        return b.get();
    }
}
