package mouse.hoi.main.common.data.effect;

import mouse.hoi.exception.EffectException;

public class AbstractEffect implements Effect{
    protected String cachedKey = null;
    @Override
    public String key() {
        if (cachedKey != null) {
            return cachedKey;
        }
        Class<? extends Effect> clazz = this.getClass();
        UseEffect annotation = clazz.getAnnotation(UseEffect.class);
        if (annotation == null) {
            throw new EffectException("No @UseEffect annotation for effect " + clazz);
        }
        cachedKey = annotation.key();
        return cachedKey;
    }
}
