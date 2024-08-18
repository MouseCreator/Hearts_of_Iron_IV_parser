package mouse.hoi.tools.parser.impl.writer.dw;

import lombok.Data;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;

@Data
public class DWComplex implements DWData {
    private final DWSimple simple;
    private final DWObject object;

    public DWComplex(DWSimple simple, DWObject object) {
        this.simple = simple;
        this.object = object;
    }

    @Override
    public void write(SpecialWriter writer) {
        simple.write(writer);
        writer.space();
        object.write(writer);
    }
}
