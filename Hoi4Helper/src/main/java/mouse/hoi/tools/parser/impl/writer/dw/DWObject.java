package mouse.hoi.tools.parser.impl.writer.dw;

import lombok.Getter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;

import java.util.ArrayList;
import java.util.List;

public class DWObject implements DWFieldList{
    @Getter
    private final List<DWField> keyValuePairs;
    private final ObjectStyle style;

    public DWObject() {
        style = ObjectStyle.DEFAULT;
        keyValuePairs = new ArrayList<>();
    }

    public DWObject(ObjectStyle style) {
        this.style = style;
        keyValuePairs = new ArrayList<>();
    }

    public void add(DWField field) {
        keyValuePairs.add(field);
    }

    @Override
    public void write(SpecialWriter writer) {
        writer.write("{");
        if (style == ObjectStyle.DEFAULT) {
            writer.incrementTabs().ln();
            for (DWField field : keyValuePairs) {
                field.write(writer);
                writer.ln();
            }
            writer.decrementTabs();
        } else {
            writer.space();
            for (DWField dwKeyValue : keyValuePairs) {
                dwKeyValue.write(writer);
                writer.space();
            }
        }
        writer.write("}");
    }

    @Override
    public List<DWField> getFields() {
        return keyValuePairs;
    }
}
