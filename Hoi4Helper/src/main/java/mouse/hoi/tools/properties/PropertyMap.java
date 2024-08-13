package mouse.hoi.tools.properties;

import mouse.hoi.exception.MissingPropertyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class PropertyMap {
    private final Map<String, String> map;

    public PropertyMap() {
        map = new HashMap<>();
    }

    public String expectedProperty(String name) {
        String val = map.get(name);
        if (val == null) {
            throw new MissingPropertyException("No property named " + name);
        }
        return val;
    }
    public <T> T expectedProperty(String name, Class<T> clazz) {
        String val = expectedProperty(name);
        return CastProperty.cast(val, clazz);
    }
    public <T> T expectedProperty(String name, Function<String, T> transform) {
        String val = expectedProperty(name);
        return transform.apply(val);
    }
    public Object expectedPropertyObject(String name, Class<?> clazz) {
        String val = expectedProperty(name);
        return CastProperty.asObject(val, clazz);
    }
    public boolean put(String name, String value) {
        String put = map.put(name, value);
        return put != null;
    }

    public Optional<Object> optionalObject(String name, Class<?> clazz) {
        Optional<String> val = optionalProperty(name);
        return val.map(k -> CastProperty.asObject(k, clazz));
    }

    public Optional<String> optionalProperty(String name) {
        String val = map.get(name);
        return Optional.ofNullable(val);
    }

    public Collection<String> keys() {
        return map.keySet();
    }
}
