package mouse.hoi.main.countries.generator.io.write;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.countries.generator.data.ExtendedColor;
import mouse.hoi.main.countries.generator.data.RGBColor;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.*;
import mouse.hoi.tools.parser.impl.writer.style.ListStyle;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtendedColorWriter implements DataWriter<ExtendedColor> {

    private final WriterSupport writerSupport;
    @Override
    public Class<ExtendedColor> forType() {
        return ExtendedColor.class;
    }

    @Override
    public DWData write(ExtendedColor ec, ObjectStyle style) {
        String prefix = ec.getPrefix();
        DWSimple simple = new DWString(prefix);
        RGBColor rgb = ec.getRgbColor();
        DWList list = writerSupport.list(ListStyle.ONE_LINE).ofIntegers(List.of(rgb.getR(), rgb.getG(), rgb.getB()));
        return new DWComplex(simple, list);
    }
}
