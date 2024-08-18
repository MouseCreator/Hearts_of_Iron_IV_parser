package mouse.hoi.tools.parser.impl.writer.dw;

import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.style.BooleanStyle;
import mouse.hoi.tools.utils.Types;

public class DWBoolean implements DWSimple{
    private final boolean value;
    private final BooleanStyle style;

    public DWBoolean(boolean value, BooleanStyle style) {
        this.value = value;
        this.style = style;
    }

    public DWBoolean(boolean value) {
        this.value = value;
        style = BooleanStyle.DEFAULT;
    }

    @Override
    public void write(SpecialWriter writer) {
        String string = Types.mapBoolean(value);
        if (style == BooleanStyle.DEFAULT) {
            writer.write(string);
        } else {
            string = string.toUpperCase();
            writer.write(string);
        }
    }
}
