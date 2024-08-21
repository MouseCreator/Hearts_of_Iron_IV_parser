package mouse.hoi.main.countries.generator.io.read;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.countries.generator.data.CountryColors;
import mouse.hoi.main.countries.generator.data.CountryColorsWrapper;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomKV;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryColorsWrapperReader implements DataReader<CountryColorsWrapper> {

    private final DomQueryService queryService;
    private final InterpreterManager interpreterManager;
    @Override
    public Class<CountryColorsWrapper> forType() {
        return CountryColorsWrapper.class;
    }

    @Override
    public CountryColorsWrapper read(DomData domData) {
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        List<DomKV> tokens = query.allTokens();
        CountryColorsWrapper wrapper = new CountryColorsWrapper();
        for (DomKV domKV : tokens) {
            String key = domKV.key().val().stringValue();
            CountryColors colors = new CountryColors();
            colors.setTag(key);
            interpreterManager.fillObject(domKV.value(), colors);
            wrapper.getColorsList().add(colors);
        }
        return wrapper;
    }
}
