package mouse.hoi.tools.utils;

import java.util.function.Consumer;

public class NotNull {
    public static <T> void then (T obj, Consumer<T> consumer) {
        if (obj == null) {
            return;
        }
        consumer.accept(obj);
    }

    public static void orThrow(Object object) {
        if (object != null) {
            return;
        }
        throw new NullPointerException("Unexpected null object!");
    }

    public static void orThrow(Object object, String message) {
        if (object != null) {
            return;
        }
        throw new NullPointerException(message);
    }
}
