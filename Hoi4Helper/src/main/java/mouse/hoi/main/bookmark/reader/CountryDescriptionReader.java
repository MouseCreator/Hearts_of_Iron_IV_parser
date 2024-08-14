package mouse.hoi.main.bookmark.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.bookmark.data.CountryDescription;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryDescriptionReader implements DataReader<CountryDescription> {

    private final Readers readers;
    @Override
    public Class<CountryDescription> forType() {
        return CountryDescription.class;
    }

    @Override
    public void onKeyValue(CountryDescription desc, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues().with(leftValue, rightValue)
                .onToken("history").setString(desc::setHistory)
                .onToken("ideology").setString(desc::setIdeology)
                .onToken("ideas").stringList().consume(desc::setIdeas)
                .onToken("focuses").stringList().consume(desc::setFocuses)
                .onToken("minor").setBoolean(desc::setMinor)
                .orElseThrow();
    }
}
