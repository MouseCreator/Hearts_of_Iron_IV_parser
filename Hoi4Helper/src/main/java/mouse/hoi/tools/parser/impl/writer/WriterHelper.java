package mouse.hoi.tools.parser.impl.writer;

import mouse.hoi.tools.parser.impl.writer.engine.WriterEngine;
import org.springframework.stereotype.Service;

@Service
public class WriterHelper {

    private WriterEngine writerEngine;
    public void write(SpecialWriter specialWriter, Object obj) {
        writerEngine.writeObj(specialWriter, obj);
    }

    public void writeWithStyle(SpecialWriter specialWriter, Object object, Object style) {
        writerEngine.writeObj(specialWriter, object, style);
    }

    public void setEngine(WriterEngine writerEngine) {
        this.writerEngine = writerEngine;
    }
}
