package mouse.hoi.tools.parser.impl.writer.n;

import mouse.hoi.tools.parser.impl.common.ForType;
import mouse.hoi.tools.parser.impl.writer.n.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;

public interface DataWriter<T> extends ForType<T> {
    DWData write(T object, ObjectStyle style);
}
