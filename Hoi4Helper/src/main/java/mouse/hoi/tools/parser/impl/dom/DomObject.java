package mouse.hoi.tools.parser.impl.dom;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomObject implements DomData {

    private final Map<DomSimple, List<DomData>> map;

    public DomObject() {
        map = new HashMap<>();
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
    }
    public List<DomData> get(DomSimple domSimple) {
        List<DomData> dataList = map.get(domSimple);
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        return dataList;
    }
}
