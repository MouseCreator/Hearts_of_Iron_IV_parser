package mouse.hoi.tools.parser.impl.writer.dw;

import mouse.hoi.tools.parser.impl.writer.SpecialWriter;

public class DWEmpty implements DWField {
    @Override
    public void write(SpecialWriter writer) {
        writer.ln();
    }
}
