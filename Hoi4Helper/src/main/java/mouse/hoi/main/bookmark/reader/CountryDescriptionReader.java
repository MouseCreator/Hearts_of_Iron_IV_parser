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
        query.onToken("history").string().set(desc::setHistory);
        query.onToken("ideology").string().set(desc::setIdeology);
        query.onToken("ideas").stringList().set(desc::setIdeas);
        query.onToken("focuses").stringList().set(desc::setFocuses);
    }
}
