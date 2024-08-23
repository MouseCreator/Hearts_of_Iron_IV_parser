package mouse.hoi.tools.parser.impl.split;

import mouse.hoi.tools.parser.impl.dom.DomKV;
import mouse.hoi.tools.parser.impl.dom.DomObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SplitByKeys {
    public List<DomObject> split(DomObject origin, List<SplitKeys> keys) {
        List<DomKV> domKVS = origin.orderedKeyValues();
        List<DomObject> result = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            result.add(new DomObject());
        }
        for (DomKV domKV : domKVS) {
            for (int i = 0; i < keys.size(); i++) {
                SplitKeys sk = keys.get(i);
                if (sk.getList().contains(domKV.key())) {
                    result.get(i).put(domKV);
                    break;
                }
            }
        }
        return result;
    }
}
