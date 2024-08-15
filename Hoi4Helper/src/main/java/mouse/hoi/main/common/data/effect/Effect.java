package mouse.hoi.main.common.data.effect;

import mouse.hoi.exception.EffectException;
import mouse.hoi.main.common.data.scope.ScopeEnum;

import java.util.List;

public interface Effect {
    List<ScopeEnum> scopes();
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
    String getEffectName();
    default boolean isSpecial() {
        return false;
    }
    default String key() {
        Class<? extends Effect> clazz = this.getClass();
        UseEffect annotation = clazz.getAnnotation(UseEffect.class);
        if (annotation == null) {
            throw new EffectException("No @UseEffect annotation for effect " + clazz);
        }
        return annotation.key();
    }
}
