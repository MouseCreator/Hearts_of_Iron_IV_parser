package mouse.hoi.main.countries.generator.io.write;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import mouse.hoi.main.countries.generator.data.CountryTag;
import mouse.hoi.main.countries.generator.data.CountryTags;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryTagsWriter implements DataWriter<CountryTags> {

    private final WriterSupport support;
    @Override
    public Class<CountryTags> forType() {
        return CountryTags.class;
    }

    @Override
    public DWData write(CountryTags tags, ObjectStyle style) {
        DWObjectBuilder build = support.build(style);
        for (CountryTag countryTag : tags.getCountryTagList()) {
            build.key(countryTag.getTag()).string(countryTag.getLocation(), StringStyle.QUOTED);
        }
        return build.get();
    }
}
