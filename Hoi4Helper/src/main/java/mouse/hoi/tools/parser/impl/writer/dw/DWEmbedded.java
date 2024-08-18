package mouse.hoi.tools.parser.impl.writer.dw;

import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;

import java.util.ArrayList;
import java.util.List;

public class DWEmbedded implements DWData{
    private final List<DWKeyValue> keyValueList;
    private final ObjectStyle objectStyle;

    public DWEmbedded(ObjectStyle style) {
        keyValueList = new ArrayList<>();
        this.objectStyle = style;
    }
    @Override
    public void write(SpecialWriter writer) {
        if (objectStyle == ObjectStyle.DEFAULT) {
            for (DWKeyValue dwKeyValue : keyValueList) {
                dwKeyValue.write(writer);
                writer.ln();
            }
        } else {
            for (DWKeyValue dwKeyValue : keyValueList) {
                dwKeyValue.write(writer);
                writer.space();
            }
        }
    }
}
