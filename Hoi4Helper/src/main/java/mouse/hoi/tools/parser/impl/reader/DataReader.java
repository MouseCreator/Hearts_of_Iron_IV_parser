package mouse.hoi.tools.parser.impl.reader;

import mouse.hoi.tools.parser.impl.common.ForType;
import mouse.hoi.tools.parser.impl.dom.DomData;

public interface DataReader<T> extends ForType<T> {
    T read(DomData domData);
}
