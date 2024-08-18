package mouse.hoi.tools.parser.impl.reader.inits;

import mouse.hoi.tools.parser.impl.common.ForType;
import mouse.hoi.tools.parser.impl.dom.DomData;

public interface InitsReader<T> extends ForType<T> {
    void read(T object, DomData domData);
}
