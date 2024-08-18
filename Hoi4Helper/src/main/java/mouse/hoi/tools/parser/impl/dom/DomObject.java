package mouse.hoi.tools.parser.impl.dom;


import java.util.*;

public class DomObject implements DomData {

    private final Map<DomSimple, List<DomData>> map;
    private final Map<String, DomSimple> casesMap;

    public DomObject() {
        map = new HashMap<>();
        casesMap = new HashMap<>();
    }
    public Collection<DomSimple> keys() {
        return map.keySet();
    }
    @Override
    public boolean isObject() {
        return true;
    }

    @Override
    public DomObject object() {
        return this;
    }

    public void put(DomSimple key, DomData domData) {
        List<DomData> dataList = map.computeIfAbsent(key, k -> new ArrayList<>());
        dataList.add(domData);
        casesMap.put(key.val().stringValue().toLowerCase(), key);
    }
    public List<DomData> get(DomSimple domSimple) {
        List<DomData> dataList = map.get(domSimple);
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        return dataList;
    }

    public List<DomData> getIgnoreCase(DomSimple domSimple) {
        DomSimple key = casesMap.get(domSimple.val().stringValue());
        return get(key);
    }
}
