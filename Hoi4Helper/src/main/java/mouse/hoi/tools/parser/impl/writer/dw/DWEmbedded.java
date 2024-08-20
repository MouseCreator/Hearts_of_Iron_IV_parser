package mouse.hoi.tools.parser.impl.writer.dw;

import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;

import java.util.ArrayList;
import java.util.List;

public class DWEmbedded implements DWFieldList{
    private final List<DWField> fieldList;
    private final ObjectStyle objectStyle;

    public DWEmbedded(ObjectStyle style) {
        fieldList = new ArrayList<>();
        this.objectStyle = style;
    }
    @Override
    public void write(SpecialWriter writer) {
        if (objectStyle == ObjectStyle.DEFAULT) {
            for (DWField dwKeyValue : fieldList) {
                dwKeyValue.write(writer);
                writer.ln();
            }
        } else {
            for (DWField dwKeyValue : fieldList) {
                dwKeyValue.write(writer);
                writer.space();
            }
        }
    }

    @Override
    public List<DWField> getFields() {
        return fieldList;
    }

    public void addFields(List<DWField> fields) {
        fieldList.addAll(fields);
    }
}
