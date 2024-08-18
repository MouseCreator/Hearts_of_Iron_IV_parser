package mouse.hoi.tools.parser.impl.writer.simple;

import mouse.hoi.tools.parser.data.GameDate;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;

public class DateWriter implements SimpleWriter<GameDate> {
    @Override
    public Class<GameDate> forType() {
        return GameDate.class;
    }

    @Override
    public void write(GameDate object, SpecialWriter specialWriter) {
        String date = object.write();
        specialWriter.write(date);
    }
}
