package mouse.hoi.tools.parser.impl.writer.dw;

import lombok.Data;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;

@Data
public class DWComplex implements DWData {
    private final DWSimple simple;
    private final DWData data;

    public DWComplex(DWSimple simple, DWData data) {
        this.simple = simple;
        this.data = data;
    }

    @Override
    public void write(SpecialWriter writer) {
        simple.write(writer);
        writer.space();
        data.write(writer);
    }
}
