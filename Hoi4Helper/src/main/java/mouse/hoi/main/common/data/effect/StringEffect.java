package mouse.hoi.main.common.data.effect;

import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.dw.DWString;

public abstract class StringEffect extends AbstractEffect{

    protected String value;

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String stringValue() {
        return value;
    }

    @Override
    public boolean isString() {
        return true;
    }

    @Override
    public DWData dwValue() {
        return new DWString(stringValue());
    }
}
