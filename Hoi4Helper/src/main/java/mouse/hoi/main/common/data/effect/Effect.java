package mouse.hoi.main.common.data.effect;

import mouse.hoi.exception.EffectException;

public interface Effect {
    default boolean isInteger() {
        return false;
    }
    default boolean isObject() {
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
    default <T> T objectValue(Class<T> cast) {
        throw new EffectException("Effect " + this + " is not an object");
    }
    String getEffectName();
    default boolean isSpecial() {
        return false;
    }
}
