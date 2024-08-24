package mouse.hoi.main.common.data.trigger;

import lombok.EqualsAndHashCode;
import mouse.hoi.exception.EffectException;

@EqualsAndHashCode
public abstract class AbstractTrigger implements Trigger {
    protected String cachedKey = null;

    @Override
    public String key() {
        if (cachedKey != null) {
            return cachedKey;
        }
        Class<? extends Trigger> clazz = this.getClass();
        UseTrigger annotation = clazz.getAnnotation(UseTrigger.class);
        if (annotation == null) {
            throw new EffectException("No @UseTrigger annotation for effect " + clazz);
        }
        cachedKey = annotation.key();
        return cachedKey;
    }
}
