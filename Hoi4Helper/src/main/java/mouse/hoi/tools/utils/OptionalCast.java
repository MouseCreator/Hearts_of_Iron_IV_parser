package mouse.hoi.tools.utils;

import java.util.Optional;

public class OptionalCast {
    public static <T> Optional<T> cast(Object object, Class<T> toCast) {
        if (toCast.isAssignableFrom(object.getClass())) {
            return Optional.of(toCast.cast(object));
        }
        return Optional.empty();
    }
}
