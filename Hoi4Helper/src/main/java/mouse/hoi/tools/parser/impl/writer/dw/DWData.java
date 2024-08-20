package mouse.hoi.tools.parser.impl.writer.dw;

import mouse.hoi.tools.parser.impl.writer.SpecialWriter;

public interface DWData {
    void write(SpecialWriter writer);
    default void onRoot(SpecialWriter writer) {
        write(writer);
    }
}
