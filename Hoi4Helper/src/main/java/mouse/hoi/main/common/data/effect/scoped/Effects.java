package mouse.hoi.main.common.data.effect.scoped;

import lombok.Getter;
import mouse.hoi.exception.EffectException;
import mouse.hoi.main.common.data.effect.Effect;
import mouse.hoi.main.common.data.effect.EffectMap;
import mouse.hoi.main.common.data.effect.UseEffect;
import mouse.hoi.main.common.data.effect.conditional.ConditionalEffects;
import mouse.hoi.main.common.data.scope.Scope;
import mouse.hoi.main.common.data.scope.Scoped;
import mouse.hoi.tools.utils.Comparators;

import java.util.*;

public class Effects implements Scoped {
    private final EffectMap simpleEffects;
    @Getter
    private final List<ConditionalEffects> conditionalEffects;
    private final Map<String, Effects> subEffects;
    private final Scope scope;
    public Effects(Scope scope) {
        this.scope = scope;
        this.simpleEffects = new EffectMap();
        this.subEffects = new HashMap<>();
        conditionalEffects = new ArrayList<>();
    }

    public boolean isEmpty() {
        filterSubEffects();
        return simpleEffects.isEmpty() && subEffects.isEmpty();
    }

    private void filterSubEffects() {
        List<String> toRemove = new ArrayList<>();
        for (String i : subEffects.keySet()) {
            Effects effectMap = subEffects.get(i);
            if (effectMap.isEmpty()) {
                toRemove.add(i);
            }
        }
        toRemove.forEach(subEffects::remove);
    }

    public EffectMap simpleEffects() {
        return simpleEffects;
    }

    public Set<String> subKeys() {
        return subEffects.keySet();
    }

    public List<String> subKeysSorted() {
        Set<String> strings = subEffects.keySet();
        return strings.stream().sorted(Comparators.numberAwareComparator).toList();
    }

    public <T> List<T> ofType(Class<T> effectClass) {
        String key = effectClass.getAnnotation(UseEffect.class).key();
        List<Effect> effects = simpleEffects.getEffects(key);
        List<T> result = new ArrayList<>();
        for (Effect e : effects) {
            result.add(effectClass.cast(e));
        }
        return result;
    }

    public Optional<Effects> getSubEffect(String subKey) {
        Effects effectMap = subEffects.get(subKey);
        return Optional.ofNullable(effectMap);
    }

    public Effects demandSubEffect(String subKey) {
        Effects effectMap = subEffects.get(subKey);
        if (effectMap == null) {
            throw new EffectException("No subkey in effect map: " + subKey);
        }
        return effectMap;
    }

    public void removeStateEffects(String subKey) {
        subEffects.remove(subKey);
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    public void addSubEffects(String key, Effects c) {
        subEffects.put(key, c);
    }
    public void addSubEffects(int key, Effects c) {
        subEffects.put(String.valueOf(key), c);
    }
}
