package mouse.hoi.main.common.data.effect;


import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.dw.DWInteger;

public abstract class IntEffect extends AbstractEffect {
    private int value;

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean isInteger() {
        return true;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public boolean isDouble() {
        return true;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public String stringValue() {
        return String.valueOf(value);
    }

    @Override
    public DWData dwValue() {
        return new DWInteger(value);
    }
}
