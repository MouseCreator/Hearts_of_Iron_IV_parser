package mouse.hoi.main.common.data.effect.store;

import mouse.hoi.exception.EffectException;

public interface EffectData {
    default boolean isSimple() { return false; }
    default boolean isObject() { return false; }
    default boolean isList() { return false; }
    default EffectDataSimple simple() {
        throw new EffectException("Effect data is not a simple value");
    }
    default EffectDataObj object() {
        throw new EffectException("Effect data is not an object value");
    }
    default EffectDataList list() {
        throw new EffectException("Effect data is not a list value");
    }
}
