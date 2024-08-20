package mouse.hoi.main.bookmark.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.bookmark.data.CountryDescription;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.inits.InitsReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryDescriptionReader implements InitsReader<CountryDescription> {

    private final DomQueryService queryService;
    @Override
    public Class<CountryDescription> forType() {
        return CountryDescription.class;
    }

    @Override
    public void read(CountryDescription desc, DomData domData) {
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        query.onToken("history").string().setOrNull(desc::setHistory);
        query.onToken("ideology").string().setOrNull(desc::setIdeology);
        query.onToken("ideas").stringList().setOrSkip(desc::setIdeas);
        query.onToken("focuses").stringList().setOrSkip(desc::setFocuses);
        query.onToken("minor").bool().setOrDefault(desc::setMinor, false);
    }
}
