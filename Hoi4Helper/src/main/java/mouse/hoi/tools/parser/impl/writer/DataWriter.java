package mouse.hoi.tools.parser.impl.writer;

import mouse.hoi.tools.parser.impl.common.ForType;
import mouse.hoi.tools.parser.impl.writer.dom.WritingDom;

public interface DataWriter<T> extends ForType<T> {
    void write(WritingDom writingDom, T object);
}
