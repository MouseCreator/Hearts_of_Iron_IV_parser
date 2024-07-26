package mouse.hoi.tools.parser.impl.writer;

import mouse.hoi.tools.parser.impl.common.ForType;

public interface DataWriter<T> extends ForType<T> {
    void write(SpecialWriter writer, T object);
}
