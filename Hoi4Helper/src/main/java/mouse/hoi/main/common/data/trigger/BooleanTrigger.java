package mouse.hoi.main.common.data.trigger;

import mouse.hoi.tools.parser.impl.writer.dw.DWBoolean;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;

public class BooleanTrigger extends AbstractTrigger {
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
