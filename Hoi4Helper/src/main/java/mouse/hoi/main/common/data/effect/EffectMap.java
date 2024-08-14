package mouse.hoi.main.common.data.effect;

import mouse.hoi.exception.EffectException;

import java.util.*;
import java.util.function.Predicate;

public class EffectMap {
    private final Map<String, List<Effect>> map;

    public EffectMap() {
        map = new HashMap<>();
    }

    public List<Effect> getEffects(String name) {
        List<Effect> effect = map.get(name);
        if (effect == null) {
            return new ArrayList<>();
        }
        return effect;
    }
    public List<Effect> findEffect(String name) {
        List<Effect> effect = map.get(name);
        if (effect == null) {
            throw new EffectException("No effects by key: " + name);
        }
        return effect;
    }
    public Optional<Effect> expectSingle(String name) {
        List<Effect> effects = map.get(name);
        if (effects == null) {
            return Optional.empty();
        }
        if (effects.size() > 1) {
            throw new EffectException("Unexpected effect '" + name + "' duplication: " + effects);
        }
        return Optional.of(effects.getFirst());
    }

    public Effect demandSingle(String name) {
        List<Effect> effects = map.get(name);
        if (effects == null) {
            throw new EffectException("No effect by key: " + name);
        }
        if (effects.size() > 1) {
            throw new EffectException("Unexpected effect '" + name + "' duplication: " + effects);
        }
        return effects.getFirst();
    }

    public boolean removeAll(String key) {
        return map.remove(key) != null;
    }
    public boolean removeEffect(Effect effect) {
        String effectName = effect.getEffectName();
        List<Effect> effects = map.get(effectName);
        if (effects.isEmpty()) {
            return false;
        }
        boolean removed = effects.remove(effect);
        if (effects.isEmpty()) {
            map.remove(effectName);
        }
        return removed;
    }
    public List<Effect> filter(String name, Predicate<Effect> filtered) {
        List<Effect> effects = map.get(name);
        if (effects == null) {
            return new ArrayList<>();
        }
        return effects.stream().filter(filtered).toList();
    }

    public <T> List<T> specialFilter(String name, Class<T> clazz, Predicate<T> filtered) {
        List<Effect> effects = map.get(name);
        if (effects == null) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        for (Effect f : effects) {
            if (clazz.isAssignableFrom(f.getClass())) {
                T cast = clazz.cast(f);
                if (filtered.test(cast)) {
                    list.add(cast);
                }
            }
        }
        return list;
    }
    public List<Effect> allEffects() {
        Collection<List<Effect>> values = map.values();
        List<Effect> result = new ArrayList<>();
        for (List<Effect> e : values) {
            result.addAll(e);
        }
        return result;
    }

    public void putEffect(Effect effect) {
        String name = effect.getEffectName();
        List<Effect> effects = map.computeIfAbsent(name, k -> new ArrayList<>());
        effects.add(effect);
    }
    public void putEffectInt(String name, int value) {
        Effect effect = new IntEffect(name, value);
        putEffect(effect);
    }
    public void putEffectDouble(String name, double d) {
        Effect effect = new DoubleEffect(name, d);
        putEffect( effect);
    }
    public void putEffectObject(String name, Object o) {
        Effect effect = new ObjectEffect(name, o);
        putEffect(effect);
    }
    public void putEffectBoolean(String name, boolean v) {
        Effect effect = new BooleanEffect(name, v);
        putEffect(effect);
    }
    public void putEffectString(String name, String value) {
        Effect effect = new StringEffect(name, value);
        putEffect(effect);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }
}
