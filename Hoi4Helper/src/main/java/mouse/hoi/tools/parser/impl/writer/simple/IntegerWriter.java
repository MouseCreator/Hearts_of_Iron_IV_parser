package mouse.hoi.tools.parser.impl.writer.simple;

import mouse.hoi.tools.parser.impl.writer.SpecialWriter;

public class IntegerWriter implements SimpleWriter<Integer>{
    @Override
    public Class<Integer> forType() {
        return Integer.class;
    }

    @Override
    public void write(Integer object, SpecialWriter specialWriter) {
        String str = String.valueOf(object);
        specialWriter.write(str);
    }
}
