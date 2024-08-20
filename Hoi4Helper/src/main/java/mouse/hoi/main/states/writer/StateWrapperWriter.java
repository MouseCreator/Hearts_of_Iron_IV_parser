package mouse.hoi.main.states.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.StateWrapper;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateWrapperWriter implements DataWriter<StateWrapper> {

    private final WriterSupport support;
    @Override
    public Class<StateWrapper> forType() {
        return StateWrapper.class;
    }
    @Override
    public DWData write(StateWrapper object, ObjectStyle style) {
        DWObjectBuilder b = support.build(style);
        b.key("state").object(object::getState);
        return b.get();
    }
}
