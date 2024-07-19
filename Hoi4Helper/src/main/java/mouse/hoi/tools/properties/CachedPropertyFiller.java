package mouse.hoi.tools.properties;

import mouse.hoi.exception.MissingPropertyException;
import mouse.hoi.exception.PropertyException;
import mouse.hoi.tools.properties.annotation.DefaultsTo;
import mouse.hoi.tools.properties.annotation.FromProperty;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Supplier;

@Service
public class CachedPropertyFiller implements PropertyFiller {
    private final Map<Class<?>, List<Field>> cache;

    public CachedPropertyFiller() {
        cache = new HashMap<>();
    }

    @Override
    public void fillObject(PropertyMap propertyMap, Object target) {
        Class<?> clazz = target.getClass();
        List<Field> fields = cache.get(clazz);
        if (fields == null) {
            fields = addToCache(clazz);
        }
        fillObject(propertyMap, target, fields);
    }

    @Override
    public <T> T fillObject(PropertyMap propertyMap, Supplier<T> supplier) {
        T t = supplier.get();
        fillObject(propertyMap, t);
        return t;
    }

    private List<Field> addToCache(Class<?> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        List<Field> result = new ArrayList<>();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(FromProperty.class)) {
                result.add(field);
            }
        }
        cache.put(clazz, result);
        return result;
    }

    private void fillObject(PropertyMap propertyMap, Object target, List<Field> fieldList) {
        for (Field field : fieldList) {
            FromProperty annotation = field.getAnnotation(FromProperty.class);
            if (annotation == null) {
                throw new IllegalStateException(
                        "Failed to fill object of class" + target.getClass() +
                        ". Unannotated field " + field + "  in cache");
            }
            String propertyName = annotation.value();
            if (propertyName.isEmpty()) {
                propertyName = field.getName();
            }
            Optional<Object> objOptional = propertyMap.optionalObject(propertyName, field.getType());
            if (objOptional.isPresent()) {
                setField(field, target, objOptional.get());
            } else {
                DefaultsTo defaults = field.getAnnotation(DefaultsTo.class);
                if (defaults == null) {
                    throw new MissingPropertyException("No property named " + propertyName);
                }
                String value = defaults.value();
                if (value.isEmpty()) {
                    continue;
                }
                Object defaultValue = CastProperty.asObject(value, field.getType());
                setField(field, target, defaultValue);
            }
        }
    }
    private void setField(Field field, Object target, Object obj) {
        field.setAccessible(true);
        try {
            field.set(target, obj);
        } catch (IllegalAccessException e) {
            throw new PropertyException(e);
        }
    }
}
