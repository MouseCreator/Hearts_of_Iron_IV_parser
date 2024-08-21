package mouse.hoi.main.states.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.Resources;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomKV;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourcesReader implements DataReader<Resources> {

    private final DomQueryService domQueryService;
    @Override
    public Class<Resources> forType() {
        return Resources.class;
    }

    @Override
    public Resources read(DomData domData) {
        DomObjectQuery query = domQueryService.validateAndQueryObject(domData);
        List<DomKV> domKVS = query.allTokens();
        Resources resources = new Resources();
        for (DomKV kv : domKVS) {
            String type = kv.key().val().stringValue();
            int amount = kv.value().simple().val().intValue();
            resources.put(type, amount);
        }
        return resources;
    }
}
