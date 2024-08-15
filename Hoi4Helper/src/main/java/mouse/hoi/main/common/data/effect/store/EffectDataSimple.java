package mouse.hoi.main.common.data.effect.store;

import mouse.hoi.exception.EffectException;

public class EffectDataSimple implements EffectData{
    private Object simplest;
    private int simplestType;
    private static final int SIMPLEST_NONE = 0;
    private static final int SIMPLEST_INT = 0;
    private static final int SIMPLEST_DOUBLE = 0;
    private static final int SIMPLEST_BOOLEAN = 0;
    private static final int SIMPLEST_STRING = 0;

    public EffectDataSimple() {
        simplest = null;
        simplestType = SIMPLEST_NONE;
    }

    public void putSimple(String key) {
        simplestType = SIMPLEST_STRING;
        simplest = key;
    }

    public void putSimple(int val) {
        simplestType = SIMPLEST_INT;
        simplest = val;
    }

    public void putSimple(double val) {
        simplestType = SIMPLEST_DOUBLE;
        simplest = val;
    }

    public void putSimple(boolean b) {
        simplestType = SIMPLEST_BOOLEAN;
        simplest = b;
    }

    public Object any() {
        return simplest;
    }

    public int intValue() {
        if (simplestType != SIMPLEST_INT) {
            throw new EffectException("Effect's simplest value is not an integer: " + simplest);
        }
        return (int) simplest;
    }

    public boolean booleanValue() {
        if (simplestType != SIMPLEST_BOOLEAN) {
            throw new EffectException("Effect's simplest value is not a boolean: " + simplest);
        }
        return (boolean) simplest;
    }

    public String stringValue() {
        if (simplestType == SIMPLEST_NONE) {
            throw new EffectException("Effect's simplest value is none!");
        }
        return simplest.toString();
    }

    public double doubleValue() {
        if (simplestType != SIMPLEST_DOUBLE) {
            throw new EffectException("Effect's simplest value is not a double: " + simplest);
        }
        return (double) simplest;
    }


    @Override
    public boolean isSimple() {
        return true;
    }

    @Override
    public EffectDataSimple simple() {
        return this;
    }
}
