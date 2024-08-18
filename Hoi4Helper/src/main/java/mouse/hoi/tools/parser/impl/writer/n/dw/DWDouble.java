package mouse.hoi.tools.parser.impl.writer.n.dw;

import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.style.DoubleStyle;

public class DWDouble implements DWSimple{

    private final double value;
    private final DoubleStyle style;

    public DWDouble(double value) {
        this.value = value;
        this.style = DoubleStyle.defaultStyle();
    }

    public DWDouble(double value, DoubleStyle style) {
        this.value = value;
        this.style = style;
    }

    @Override
    public void write(SpecialWriter writer) {
        String string = style.styled(value);
        writer.write(string);
    }
}
