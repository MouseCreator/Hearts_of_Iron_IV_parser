package mouse.hoi.main.bookmark.writer;

import mouse.hoi.main.bookmark.data.Bookmark;
import mouse.hoi.main.bookmark.data.CountryDescription;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.style.BooleanStyle;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookmarkWriter implements DataWriter<Bookmark> {
    @Override
    public Class<Bookmark> forType() {
        return Bookmark.class;
    }

    @Override
    public void write(SpecialWriter writer, Bookmark object) {
        writer
                .key("name").value(object::getName, StringStyle.QUOTED)
                .key("desc").value(object::getDesc, StringStyle.QUOTED)
                .key("date").value(object::getDate)
                .key("picture").value(object::getPicture, StringStyle.QUOTED)
                .key("default").value(object::isDefault, BooleanStyle.DEFAULT)
                .key("default_country").value(object::getDefaultCountry, StringStyle.QUOTED)
                .key("effect").object(object::getEffects);
        List<CountryDescription> countryDescriptionList = object.getCountryDescriptionList();
        for (CountryDescription countryDescription : countryDescriptionList) {
            String tag = countryDescription.getTag();
            writer.write(tag, StringStyle.QUOTED).eqb();
            writer.writeObject(countryDescription);
            writer.endObj();
        }
    }
}
