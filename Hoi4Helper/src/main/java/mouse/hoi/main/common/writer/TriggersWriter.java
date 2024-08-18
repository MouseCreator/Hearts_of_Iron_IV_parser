package mouse.hoi.main.common.writer;

import mouse.hoi.main.common.data.trigger.Triggers;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import org.springframework.stereotype.Service;

@Service
public class TriggersWriter implements DataWriter<Triggers> {
    @Override
    public Class<Triggers> forType() {
        return Triggers.class;
    }

    @Override
    public void write(SpecialWriter writer, Triggers object) {
        writer.beginObj().lineComment("NO TRIGGERS!").endObj();
    }
}
