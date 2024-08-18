package mouse.hoi.tools.parser.impl.writer.dw;

import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;

public class DWString implements DWSimple {
    private final String string;
    private final StringStyle style;
    public DWString(String str, StringStyle style) {
        this.string = str;
        this.style = style;
    }
    public DWString(String str) {
        this.string = str;
        this.style = StringStyle.DEFAULT;
    }

    @Override
    public void write(SpecialWriter writer) {
        if (style == StringStyle.QUOTED) {
            String str = '"' + string + '"';
            writer.write(str);
        } else {
            writer.write(string);
        }
    }
}
