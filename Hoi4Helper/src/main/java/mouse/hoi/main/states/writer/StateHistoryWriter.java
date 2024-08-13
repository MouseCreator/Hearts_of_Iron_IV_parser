package mouse.hoi.main.states.writer;

import mouse.hoi.main.states.data.StateHistory;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import org.springframework.stereotype.Service;

@Service
public class StateHistoryWriter implements DataWriter<StateHistory> {
    @Override
    public Class<StateHistory> forType() {
        return StateHistory.class;
    }

    @Override
    public void write(SpecialWriter writer, StateHistory history) {
        writer.beginObj().
                key("owner").value(history::getOwner).
                endObj();
    }
}
