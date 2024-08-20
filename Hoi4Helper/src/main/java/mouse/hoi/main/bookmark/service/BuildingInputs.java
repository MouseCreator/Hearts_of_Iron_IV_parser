package mouse.hoi.main.bookmark.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BuildingInputs {
    private final Map<String,BuildingDef> map;

    public BuildingInputs() {
        map = new HashMap<>();
    }
    public Set<String> keys() {
        return map.keySet();
    }
    public BuildingDef getDef(String tag) {
        return map.get(tag);
    }
    public void put(String tag, BuildingDef def) {
        map.put(tag, def);
    }
}
