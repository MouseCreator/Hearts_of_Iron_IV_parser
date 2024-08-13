package mouse.hoi.main.states.writer;

import mouse.hoi.main.states.data.StateWrapper;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import org.springframework.stereotype.Service;

@Service
public class StateWrapperWriter implements DataWriter<StateWrapper> {
    @Override
    public Class<StateWrapper> forType() {
        return StateWrapper.class;
    }

    @Override
    public void write(SpecialWriter writer, StateWrapper object) {
        writer.key("state").object(object::getState);
    }
}
