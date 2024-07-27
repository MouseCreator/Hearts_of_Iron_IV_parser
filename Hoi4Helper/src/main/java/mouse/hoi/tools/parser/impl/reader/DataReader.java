package mouse.hoi.tools.parser.impl.reader;

import mouse.hoi.exception.UnsupportedReaderException;
import mouse.hoi.tools.parser.impl.common.ForType;
import mouse.hoi.tools.parser.impl.reader.helper.DefaultInitializer;
import mouse.hoi.tools.parser.impl.reader.lr.ComplexValue;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import mouse.hoi.tools.parser.impl.reader.lr.SimpleValue;

public interface DataReader<T> extends ForType<T> {
    default T initialize() {
        Class<T> type = forType();
        return DefaultInitializer.init(type);
    }
    default void onKeyValue(T object, LeftValue leftValue, RightValue rightValue) {
        String msg = "Reader " + this.forType() + " does not support key-value nodes, however was called for <" +
        leftValue + "> = <" + rightValue + ">";
        throw new UnsupportedReaderException(msg);
    }
    default void onSimple(T object, SimpleValue simpleValue) {
        String msg = "Reader " + this.forType() + " does not support simple nodes, however was called for <" + simpleValue + ">";
        throw new UnsupportedReaderException(msg);
    }
    default void onComplex(T object, ComplexValue complexValue) {
        String msg = "Reader " + this.forType() + " does not support complex nodes, however was called for <" + complexValue + ">";
        throw new UnsupportedReaderException(msg);
    }
}
