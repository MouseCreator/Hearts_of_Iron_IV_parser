package mouse.hoi.tools.parser.impl.writer.n.dw;

import lombok.Getter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;

import java.util.ArrayList;
import java.util.List;

public class DWObject implements DWData{
    @Getter
    private final List<DWKeyValue> keyValuePairs;
    private final ObjectStyle style;

    public DWObject() {
        style = ObjectStyle.DEFAULT;
        keyValuePairs = new ArrayList<>();
    }

    public DWObject(ObjectStyle style) {
        this.style = style;
        keyValuePairs = new ArrayList<>();
    }

    public void add(DWKeyValue dwKeyValue) {
        keyValuePairs.add(dwKeyValue);
    }

    @Override
    public void write(SpecialWriter writer) {
        writer.write("{");
        if (style == ObjectStyle.DEFAULT) {
            writer.incrementTabs().ln();
            for (DWKeyValue dwKeyValue : keyValuePairs) {
                dwKeyValue.write(writer);
                writer.ln();
            }
            writer.decrementTabs();
        } else {
            writer.space();
            for (DWKeyValue dwKeyValue : keyValuePairs) {
                dwKeyValue.write(writer);
                writer.space();
            }
        }
        writer.write("}");
    }
}
