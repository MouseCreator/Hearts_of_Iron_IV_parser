package mouse.hoi.main.bookmark.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.bookmark.data.CountryDescription;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ListStyle;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import mouse.hoi.tools.utils.NotNull;
import mouse.hoi.tools.utils.TestIf;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryDescriptionWriter implements DataWriter<CountryDescription> {

    private final WriterSupport writerSupport;
    @Override
    public Class<CountryDescription> forType() {
        return CountryDescription.class;
    }

    @Override
    public DWData write(CountryDescription country, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        TestIf.ifTrue(country::isMinor).then(t -> b.key("minor").bool(t));
        NotNull.supply(country::getHistory, h -> b.key("history").string(h, StringStyle.QUOTED));
        NotNull.supply(country::getIdeology, i -> b.key("ideology").string(i));
        TestIf.supply(country::getIdeas, l->!l.isEmpty()).then(t -> b.key("ideas").stringList(t, ListStyle.THREE_LINES));
        TestIf.supply(country::getFocuses, l->!l.isEmpty()).then(t -> b.key("focuses").stringList(t, ListStyle.THREE_LINES));
        return b.get();
    }
}
