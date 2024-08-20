package mouse.hoi.tools.parser.impl.split;

import mouse.hoi.tools.parser.impl.dom.DomKV;
import mouse.hoi.tools.parser.impl.dom.DomObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorySplitManager {
    public List<DomObject> split(DomObject origin, List<CategorySplitDescription> descriptions) {
        List<DomKV> domKVS = origin.orderedKeyValues();
        List<DomObject> result = new ArrayList<>();
        for (int i = 0; i < descriptions.size(); i++) {
            result.add(new DomObject());
        }
        for (DomKV domKV : domKVS) {
            for (int i = 0; i < descriptions.size(); i++) {
                CategorySplitDescription description = descriptions.get(i);
                if (description.test(domKV)) {
                    result.get(i).put(domKV);
                    break;
                }
            }
        }
        return result;
    }
}
