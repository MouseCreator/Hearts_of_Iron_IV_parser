package mouse.hoi.main.common.data.effect.store;

import mouse.hoi.exception.EffectException;

import java.util.*;

public class EffectDataObj implements EffectData{

    @Override
    public boolean isObject() {
        return true;
    }

    private final Map<String, Object> map;

    public EffectDataObj() {
        map = new HashMap<>();
    }

    public void put(String key, String value) {
        map.put(key, value);
    }

    private Object getFromMapOrThrow(String key) {
        Object o = map.get(key);
        if (o == null) {
            throw new EffectException("No key in effect map: " + key);
        }
        return o;
    }

    private Optional<Object> getMapOptional(String key) {
        Object o = map.get(key);
        return Optional.of(o);
    }

    public void put(String key, double value) {
        map.put(key, value);
    }

    public void put(String key, int value) {
        map.put(key, value);
    }

    public void put(String key, boolean value) {
        map.put(key, value);
    }

    public void put(String key, EffectData value) {
        map.put(key, value);
    }

    public Set<String> keys() {
        return map.keySet();
    }

    public String getString(String key) {
        Object obj = getFromMapOrThrow(key);
        if (obj instanceof String str) {
            return str;
        }
        throw new EffectException("Effect value is not a string " + key + ": " + obj);
    }

    public int getInteger(String key) {
        Object obj = getFromMapOrThrow(key);
        if (obj instanceof Integer i) {
            return i;
        }
        throw new EffectException("Effect value is not an integer " + key + ": " + obj);
    }

    public boolean getBoolean(String key) {
        Object obj = getFromMapOrThrow(key);
        if (obj instanceof Boolean b) {
            return b;
        }
        throw new EffectException("Effect value is not a boolean " + key + ": " + obj);
    }

    public double getDouble(String key) {
        Object obj = getFromMapOrThrow(key);
        if (obj instanceof Double d) {
            return d;
        }
        if (obj instanceof Integer i) {
            return i;
        }
        throw new EffectException("Effect value is not a double " + key + ": " + obj);
    }

    public EffectData getData(String key) {
        Object obj = getFromMapOrThrow(key);
        if (obj instanceof EffectData effectData) {
            return effectData;
        }
        throw new EffectException("Effect value is not an effect data " + key + ": " + obj);
    }

    public Optional<Integer> optionalInteger(String key) {
        Optional<Object> optional = getMapOptional(key);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Object obj = optional.get();
        if (obj instanceof Integer integer) {
            return Optional.of(integer);
        }
        return Optional.empty();
    }
    public Optional<Double> optionalDouble(String key) {
        Optional<Object> optional = getMapOptional(key);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Object obj = optional.get();
        if (obj instanceof Double d) {
            return Optional.of(d);
        }
        if (obj instanceof Integer i) {
            return Optional.of((double)i);
        }
        return Optional.empty();
    }

    public Optional<EffectData> optionalData(String key) {
        Optional<Object> optional = getMapOptional(key);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Object obj = optional.get();
        if (obj instanceof EffectData d) {
            return Optional.of(d);
        }
        return Optional.empty();
    }

    public Optional<Boolean> optionalBoolean(String key) {
        Optional<Object> optional = getMapOptional(key);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Object obj = optional.get();
        if (obj instanceof Boolean b) {
            return Optional.of(b);
        }
        return Optional.empty();
    }

    public Optional<String> optionalString(String key) {
        Optional<Object> optional = getMapOptional(key);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Object obj = optional.get();
        if (obj instanceof String s) {
            return Optional.of(s);
        }
        return Optional.empty();
    }
    @Override
    public EffectDataObj object() {
        return this;
    }

    public Object getAny(String key) {
        return map.get(key);
    }
}
