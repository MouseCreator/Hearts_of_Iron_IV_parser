package mouse.hoi.main.common.data.effect;

import mouse.hoi.exception.EffectException;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;

public interface Effect {
    default boolean isInteger() {
        return false;
    }
    default boolean isString() {
        return false;
    }
    default boolean isDouble() {
        return false;
    }
    default boolean isBoolean() {
        return false;
    }
    default int intValue() {
        throw new EffectException("Effect " + this + " is not an integer");
    }
    default double doubleValue() {
        throw new EffectException("Effect " + this + " is not a double");
    }
    default boolean booleanValue() {
        throw new EffectException("Effect " + this + " is not a boolean");
    }
    default String stringValue() {
        throw new EffectException("Effect " + this + " is not a string");
    }
    default boolean isSpecial() {
        return false;
    }
    String key();
    default DWData dwValue()  {
        throw new EffectException("Effect " + this + " has no DW value");
    }
}
