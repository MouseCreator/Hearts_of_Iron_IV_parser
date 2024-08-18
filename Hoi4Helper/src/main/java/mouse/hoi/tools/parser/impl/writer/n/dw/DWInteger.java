package mouse.hoi.tools.parser.impl.writer.n.dw;

import mouse.hoi.tools.parser.impl.writer.SpecialWriter;

public class DWInteger implements DWSimple{
    private final int value;
    public DWInteger(int value) {
        this.value = value;
    }
    @Override
    public void write(SpecialWriter writer) {
        writer.write(value);
    }
}
