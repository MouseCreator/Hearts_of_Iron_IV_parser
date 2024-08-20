package mouse.hoi.main.common.writer;

import mouse.hoi.exception.WriterException;
import mouse.hoi.main.common.data.trigger.Triggers;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.dw.DWString;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import org.springframework.stereotype.Service;

@Service
public class TriggersWriter implements DataWriter<Triggers> {
    @Override
    public Class<Triggers> forType() {
        return Triggers.class;
    }
    @Override
    public DWData write(Triggers object, ObjectStyle style) {
        //TODO:
        throw new WriterException("No trigger writer yer");
    }
}
