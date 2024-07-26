package mouse.hoi.tools.parser.impl.reader;

import mouse.hoi.tools.parser.impl.common.ForType;
import mouse.hoi.tools.parser.impl.reader.helper.DefaultInitializer;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import mouse.hoi.tools.parser.impl.reader.lr.SimpleValue;

public interface DataReader<T> extends ForType<T> {
    default T initialize() {
        Class<T> type = forType();
        return DefaultInitializer.init(type);
    }
    default void onKeyValue(T object, LeftValue leftValue, RightValue rightValue) {
        throw new UnsupportedOperationException();
    }
    default void onSimple(T object, SimpleValue simpleValue) {
        throw new UnsupportedOperationException();
    }
}
