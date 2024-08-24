package mouse.hoi.main.common.data.trigger;

import mouse.hoi.exception.TriggerException;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;

public interface Trigger {

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
        throw new TriggerException("Trigger " + this + " is not an integer");
    }
    default double doubleValue() {
        throw new TriggerException("Trigger " + this + " is not a double");
    }
    default boolean booleanValue() {
        throw new TriggerException("Trigger " + this + " is not a boolean");
    }
    default String stringValue() {
        throw new TriggerException("Trigger " + this + " is not a string");
    }
    default boolean isSpecial() {
        return false;
    }
    String key();
    default DWData dwValue()  {
        throw new TriggerException("Trigger " + this + " has no DW value");
    }
}
