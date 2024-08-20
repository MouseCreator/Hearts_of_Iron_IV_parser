package mouse.hoi.tools.parser.impl.writer.dw;

import mouse.hoi.tools.parser.impl.writer.SpecialWriter;

public class DWKeyValue implements DWField {

    private final DWSimple key;
    private final DWData target;

    public DWKeyValue(DWSimple key, DWData target) {
        this.key = key;
        this.target = target;
    }

    @Override
    public void write(SpecialWriter writer) {
        key.write(writer);
        writer.write(" = ");
        target.write(writer);
    }
}
