package mouse.hoi.main.bookmark.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.bookmark.data.Bookmark;
import mouse.hoi.main.bookmark.data.CountryDescription;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import mouse.hoi.tools.utils.TestIf;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BookmarkWriter implements DataWriter<Bookmark> {

    private final WriterSupport writerSupport;
    @Override
    public Class<Bookmark> forType() {
        return Bookmark.class;
    }

    @Override
    public DWData write(Bookmark object, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        b.key("name").string(object::getName, StringStyle.QUOTED);
        b.key("desc").string(object::getDesc, StringStyle.QUOTED);
        b.key("date").date(object::getDate);
        TestIf.ifTrue(object::isDefault).then(t -> b.key("default").bool(t));
        b.key("default_country").string(object::getDefaultCountry, StringStyle.QUOTED);
        b.key("effect").object(object::getEffects);
        List<CountryDescription> countryDescriptionList = object.getCountryDescriptionList();
        for (CountryDescription description : countryDescriptionList) {
            String tag = description.getTag();
            b.key(tag, StringStyle.QUOTED).objectRaw(description);
        }
        return b.get();
    }
}
