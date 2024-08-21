package mouse.hoi.main.countries.generator.io.read;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.countries.generator.data.CountryTag;
import mouse.hoi.main.countries.generator.data.CountryTags;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomKV;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryTagsReader implements DataReader<CountryTags> {
    private final DomQueryService queryService;
    @Override
    public Class<CountryTags> forType() {
        return CountryTags.class;
    }

    @Override
    public CountryTags read(DomData domData) {
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        List<DomKV> domKVS = query.allTokens();
        CountryTags tags = new CountryTags();
        for (DomKV domKV : domKVS) {
            String tag = domKV.key().val().stringValue();
            String value = domKV.value().simple().val().stringValue();
            CountryTag tagObj = new CountryTag(tag, value);
            tags.getCountryTagList().add(tagObj);
        }
        return tags;
    }
}
