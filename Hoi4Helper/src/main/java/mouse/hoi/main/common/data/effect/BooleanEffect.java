package mouse.hoi.main.common.data.effect;

import mouse.hoi.tools.parser.impl.writer.dw.DWBoolean;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;

public abstract class BooleanEffect extends AbstractEffect{
    private boolean value;

    public void setValue(boolean v) {
        this.value = v;
    }

    @Override
    public boolean isBoolean() {
        return true;
    }

    @Override
    public boolean booleanValue() {
        return value;
    }

    @Override
    public DWData dwValue() {
        return new DWBoolean(value);
    }
}
