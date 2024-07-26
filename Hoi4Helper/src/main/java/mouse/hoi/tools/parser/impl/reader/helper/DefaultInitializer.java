package mouse.hoi.tools.parser.impl.reader.helper;

import mouse.hoi.tools.parser.impl.reader.Inits;

public class DefaultInitializer {
    public static <T> T init(Class<T> clazz) {
        T t;
        try {
            t = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Default initialization failed for " + clazz, e);
        }
        if (t instanceof Inits i) {
            i.initialize();
        }
        return t;
    }
}
