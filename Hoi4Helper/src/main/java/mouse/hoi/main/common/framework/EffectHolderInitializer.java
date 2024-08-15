package mouse.hoi.main.common.framework;

import lombok.Getter;
import mouse.hoi.main.common.data.scope.ScopeEnum;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class EffectHolderInitializer {
    private final EffectsHolder effectsHolder;
    private final EffectsLoader effectsLoader;

    public EffectHolderInitializer(EffectsLoader effectsLoader) {
        this.effectsLoader = effectsLoader;
        effectsHolder = new EffectsHolder();
        done = false;
    }

    @Getter
    private boolean done;
    public EffectsHolder effectsHolder() {
        if (done) {
            return effectsHolder;
        }
        Collection<EffectDescription> effectDescriptions = effectsLoader.loadEffects();
        for (EffectDescription description : effectDescriptions) {
            String key = description.getKey();
            Class<?> effectClass = description.getEffectClass();
            List<ScopeEnum> scopeList = description.getScopeList();
            for (ScopeEnum scopeEnum : scopeList) {
                effectsHolder.put(key, scopeEnum, effectClass);
            }
        }
        done = true;
        return effectsHolder;
    }
}
