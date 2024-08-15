package mouse.hoi.main.common.framework;

import mouse.hoi.main.common.data.effect.UseEffect;
import mouse.hoi.main.common.data.scope.ScopeEnum;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.springframework.stereotype.Service;

import java.lang.reflect.Modifier;
import java.util.*;

@Service
public class EffectsLoaderImpl implements EffectsLoader {
    private final static String basePkg = "mouse.hoi.main.common.data.effect.effects";
    public Collection<EffectDescription> loadEffects() {
        Reflections reflections = new Reflections(basePkg,
                Scanners.SubTypes, Scanners.TypesAnnotated);

        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(UseEffect.class);
        List<Class<?>> resultClasses = new ArrayList<>(annotatedClasses);
        for (Class<?> clazz : annotatedClasses) {
            Class<?>[] nestedClasses = clazz.getDeclaredClasses();
            for (Class<?> nestedClass : nestedClasses) {
                if (Modifier.isStatic(nestedClass.getModifiers()) &&
                        nestedClass.isAnnotationPresent(UseEffect.class)) {
                    resultClasses.add(nestedClass);
                }
            }
        }
        return toEffectDescription(resultClasses);

    }

    private Collection<EffectDescription> toEffectDescription(List<Class<?>> resultClasses) {
        List<EffectDescription> effectDescriptions = new ArrayList<>();
        for (Class<?> clazz : resultClasses) {
            UseEffect annotation = clazz.getAnnotation(UseEffect.class);
            String key = annotation.key();
            ScopeEnum[] scope = annotation.scope();
            List<ScopeEnum> list = Arrays.asList(scope);
            EffectDescription effectDescription = new EffectDescription(clazz, key, list);
            effectDescriptions.add(effectDescription);
        }
        return effectDescriptions;
    }
}
