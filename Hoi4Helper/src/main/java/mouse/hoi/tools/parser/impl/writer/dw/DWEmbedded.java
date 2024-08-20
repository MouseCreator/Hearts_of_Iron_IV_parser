package mouse.hoi.tools.parser.impl.writer.dw;

import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;

import java.util.ArrayList;
import java.util.List;

public class DWEmbedded implements DWField{
    private final DWData dwData;

    public DWEmbedded(DWData dwData) {
        this.dwData = dwData;
    }

    @Override
    public void write(SpecialWriter writer) {
        writer.write(dwData);
    }
}
