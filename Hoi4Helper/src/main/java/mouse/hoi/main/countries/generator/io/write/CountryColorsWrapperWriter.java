package mouse.hoi.main.countries.generator.io.write;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.countries.generator.data.CountryColors;
import mouse.hoi.main.countries.generator.data.CountryColorsWrapper;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryColorsWrapperWriter implements DataWriter<CountryColorsWrapper> {
    private final WriterSupport writerSupport;

    @Override
    public Class<CountryColorsWrapper> forType() {
        return CountryColorsWrapper.class;
    }

    @Override
    public DWData write(CountryColorsWrapper wrapper, ObjectStyle style) {
        List<CountryColors> colorsList = wrapper.getColorsList();
        DWObjectBuilder build = writerSupport.build(style);
        for (CountryColors c : colorsList) {
            build.embedded(()->c);
        }
        return build.get();
    }
}
