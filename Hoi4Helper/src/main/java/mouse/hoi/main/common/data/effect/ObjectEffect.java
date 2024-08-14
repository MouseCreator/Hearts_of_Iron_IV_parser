package mouse.hoi.main.common.data.effect;

import mouse.hoi.exception.EffectException;

public class ObjectEffect implements Effect{
    private Object object;
    private String effectName;

    public ObjectEffect(String name, Object o) {
        this.effectName = name;
        this.object = o;
    }

    @Override
    public boolean isObject() {
        return true;
    }

    @Override
    public <T> T objectValue(Class<T> cast) {
        try {
            return cast.cast(object);
        } catch (Exception e) {
            throw new EffectException("Unable to cast object", e);
        }
    }

    @Override
    public String getEffectName() {
        return effectName;
    }
}
