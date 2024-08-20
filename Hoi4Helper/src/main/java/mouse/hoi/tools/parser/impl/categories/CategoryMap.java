package mouse.hoi.tools.parser.impl.categories;

import mouse.hoi.tools.parser.impl.dom.DomKV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryMap {

    private final Map<TokenCategory, List<DomKV>> map;

    public CategoryMap() {
        map = new HashMap<>();
    }

    public void addToCategory(TokenCategory category, DomKV domKV) {
        List<DomKV> domKVS = map.computeIfAbsent(category, k -> new ArrayList<>());
        domKVS.add(domKV);
    }

    public List<DomKV> onCategory(TokenCategory tokenCategory) {
        return map.getOrDefault(tokenCategory, new ArrayList<>());
    }
}
