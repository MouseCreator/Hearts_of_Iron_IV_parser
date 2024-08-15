package mouse.hoi.tools.reflect;

import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
@Service
public class ObjectInitializer {
    public <T> T useDefaultConstructor(Class<T> c) {
        try {
            return c.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
