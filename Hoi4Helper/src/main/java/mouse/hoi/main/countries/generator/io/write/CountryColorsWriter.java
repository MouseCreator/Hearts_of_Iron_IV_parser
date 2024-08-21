package mouse.hoi.main.countries.generator.io.write;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.countries.generator.data.CountryColors;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryColorsWriter implements DataWriter<CountryColors> {

    private final WriterSupport support;
    @Override
    public Class<CountryColors> forType() {
        return CountryColors.class;
    }

    @Override
    public DWData write(CountryColors colors, ObjectStyle style) {
        DWObjectBuilder b = support.build(style);
        DWObjectBuilder s = support.build(ObjectStyle.DEFAULT);
        s.key("color").object(colors::getColor);
        s.key("color_ui").object(colors::getUiColor);
        b.key(colors.getTag()).value(s.get());
        return b.get();
    }
}
