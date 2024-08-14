package mouse.hoi.main.bookmark.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.bookmark.data.Bookmark;
import mouse.hoi.main.bookmark.data.CountryDescription;
import mouse.hoi.tools.database.TokenTester;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkReader implements DataReader<Bookmark> {

    private final Readers readers;
    private final TokenTester test;
    @Override
    public Class<Bookmark> forType() {
        return Bookmark.class;
    }

    @Override
    public void onKeyValue(Bookmark bookmark, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues().with(leftValue, rightValue)
                .onToken("name").setString(bookmark::setName)
                .onToken("desc").setString(bookmark::setDesc)
                .onToken("date").setString(bookmark::setDate)
                .onToken("picture").setString(bookmark::setPicture)
                .onToken("default").setBoolean(bookmark::setDefault)
                .onToken("default_country").setString(bookmark::setDefaultCountry)
                .onToken("effect").mapBlock(b -> readers.interpreters().read(GlobalEffects.class, b)).consume(bookmark::setEffects)
                .rememberString()
                                    .test(test::isCountryTag)
                                    .map(CountryDescription::new)
                                    .onBlock(readers.interpreters()::readObj)
                                    .res().push(bookmark::getCountryDescriptionList)
                .orElseThrow();

    }
}
