package mouse.hoi.main.common.framework;

import lombok.RequiredArgsConstructor;
import mouse.hoi.exception.EffectException;
import mouse.hoi.main.common.data.effect.Effect;
import mouse.hoi.tools.reflect.ObjectInitializer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EffectFactory {
    private final ObjectInitializer objectInitializer;
    public Effect createEffect(Class<?> clazz) {
        Object obj = objectInitializer.useDefaultConstructor(clazz);
        if (obj instanceof Effect e) {
            return e;
        }
        throw new EffectException("Object is not an effect: " + obj);
    }
}
