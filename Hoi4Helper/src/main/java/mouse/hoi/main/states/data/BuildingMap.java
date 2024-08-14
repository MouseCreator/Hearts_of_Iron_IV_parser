package mouse.hoi.main.states.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BuildingMap {
    private final Map<String, Integer> map;

    public BuildingMap() {
        map = new HashMap<>();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void remove(String typeTarget) {
        map.remove(typeTarget);
    }

    public void put(String type, int level) {
        map.put(type, level);
    }
    public int getLevel(String type) {
        Integer i = map.get(type);
        if (i == null) {
            return 0;
        }
        return i;
    }

    public Collection<String> keys() {
        return map.keySet();
    }
}
