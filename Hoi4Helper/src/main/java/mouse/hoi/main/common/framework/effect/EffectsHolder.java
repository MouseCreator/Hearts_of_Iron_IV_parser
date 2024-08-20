package mouse.hoi.main.common.framework.effect;

import mouse.hoi.main.common.data.scope.ScopeEnum;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class EffectsHolder {
    private final Map<KeyScopePair, Class<?>> map;
    private record KeyScopePair(String key, ScopeEnum scope) {
    }
    public void put(String key, ScopeEnum scopeEnum, Class<?> effect) {
        KeyScopePair pair = new KeyScopePair(key, scopeEnum);
        this.map.put(pair, effect);
    }
    public EffectsHolder() {
        map = new HashMap<>();
    }
    public Optional<Class<?>> onEffect(String key, ScopeEnum scopeEnum) {
        KeyScopePair pair = new KeyScopePair(key, scopeEnum);
        Class<?> clazz = map.get(pair);

        return Optional.ofNullable(clazz);
    }
}
