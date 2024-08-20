package mouse.hoi.main.bookmark.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.bookmark.data.Bookmark;
import mouse.hoi.main.bookmark.data.CountryDescription;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.main.common.data.scope.GlobalScope;
import mouse.hoi.main.common.tester.TokenTester;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkReader implements DataReader<Bookmark> {

    private final DomQueryService queryService;
    private final TokenTester test;
    @Override
    public Class<Bookmark> forType() {
        return Bookmark.class;
    }
    @Override
    public Bookmark read(DomData domData) {
        Bookmark bookmark = new Bookmark();
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        query.requireToken("name").string().set(bookmark::setName);
        query.requireToken("desc").string().set(bookmark::setDesc);
        query.requireToken("date").date().set(bookmark::setDate);
        query.requireToken("picture").string().set(bookmark::setPicture);
        query.onToken("default").bool().setOrDefault(bookmark::setDefault, false);
        query.requireToken("default_country").string().set(bookmark::setDefaultCountry);
        Effects effects = new Effects(new GlobalScope());
        query.onToken("effect").object(()->effects).setOrDefault(bookmark::setEffects, effects);
        List<String> list = query.allTokens().stream().map(t -> t.key().val().stringValue()).filter(this::onlyCountryTags).toList();
        for (String string : list) {
            CountryDescription description = new CountryDescription(string);
            query.requireToken(string).objectRaw(description).push(bookmark::getCountryDescriptionList);
        }
        return bookmark;
    }

    private boolean onlyCountryTags(String string) {
        if (string.equals("---")) {
            return true;
        }
        return test.isCountryTag(string);
    }
}
