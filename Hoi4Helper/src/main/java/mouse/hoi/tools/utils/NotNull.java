package mouse.hoi.tools.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

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

    public static <T> void supply(Supplier<T> supplier, Consumer<T> consumer) {
        T t = supplier.get();
        if (t == null) {
            return;
        }
        consumer.accept(t);
    }
}
