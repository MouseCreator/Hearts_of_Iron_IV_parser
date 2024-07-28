package mouse.hoi.tools.parser.impl.writer;

import mouse.hoi.exception.WriterException;
import mouse.hoi.tools.parser.impl.writer.engine.WriterEngine;
import org.springframework.stereotype.Service;

@Service
public class WriterHelper {

    private WriterEngine writerEngine;
    public void write(SpecialWriter specialWriter, Object obj) {
        try {
            writerEngine.writeObj(specialWriter, obj);
        } catch (Exception e) {
            throw new WriterException("Failed to write object " + obj, e);
        }
    }

    public void writeWithStyle(SpecialWriter specialWriter, Object object, Object style) {
        if (object == null) {
            throw new WriterException("Cannot write null object!");
        }
        try {
            writerEngine.writeObj(specialWriter, object, style);
        } catch (Exception e) {
            throw new WriterException("Failed to write object " + object, e);
        }
    }

    public void setEngine(WriterEngine writerEngine) {
        this.writerEngine = writerEngine;
    }
}
