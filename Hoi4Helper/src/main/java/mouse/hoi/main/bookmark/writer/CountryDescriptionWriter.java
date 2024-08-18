package mouse.hoi.main.bookmark.writer;

import mouse.hoi.main.bookmark.data.CountryDescription;
import mouse.hoi.tools.parser.impl.writer.style.ListStyle;
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
                .key("minor").testValue(country::isMinor).printIfLn("true", Object::toString)
                .key("history").testValue(country::getHistory).printIfNotNullLn(StringStyle.QUOTED).ln();
       if (country.getIdeology() != null) {
           writer.write("ideology").eq().write(country.getIdeology());
       }
        writer.list("ideas", ListStyle.MULTI_LINE).simple(country::getIdeas).ln()
        .list("focuses", ListStyle.MULTI_LINE).simple(country::getFocuses);
    }
}
