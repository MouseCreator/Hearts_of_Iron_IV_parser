package mouse.hoi.tools.properties;

import java.util.function.Supplier;

public interface PropertyFiller {
    void fillObject(PropertyMap propertyMap, Object object);
    <T> T fillObject(PropertyMap propertyMap, Supplier<T> supplier);
}
