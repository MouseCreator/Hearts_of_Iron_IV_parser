package mouse.hoi.main.bookmark.writer;

import mouse.hoi.main.bookmark.data.CountryDescription;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.ListStyle;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;
import org.springframework.stereotype.Service;

@Service
public class CountryDescriptionWriter implements DataWriter<CountryDescription> {
    @Override
    public Class<CountryDescription> forType() {
        return CountryDescription.class;
    }

    @Override
    public void write(SpecialWriter writer, CountryDescription country) {
        writer
                .key("minor").testValue(country::isMinor).printIf(i->i)
                .key("history").value(country::getHistory, StringStyle.QUOTED)
                .key("ideology").value(country::getIdeology)
                .list("ideas", ListStyle.MULTI_LINE).simple(country::getIdeas)
                .list("focuses", ListStyle.MULTI_LINE).simple(country::getFocuses);
    }
}
