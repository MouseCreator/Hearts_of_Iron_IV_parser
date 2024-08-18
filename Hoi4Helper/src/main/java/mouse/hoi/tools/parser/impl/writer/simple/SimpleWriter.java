package mouse.hoi.tools.parser.impl.writer.simple;

import mouse.hoi.tools.parser.impl.common.ForType;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;

public interface SimpleWriter<T> extends ForType<T> {
    void write(T object, SpecialWriter specialWriter);
}
