package mouse.hoi.main.bookmark.service;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public class PreparedBuildings {
    private Map<String, Integer> map;

    public PreparedBuildings() {
        map = new HashMap<>();
    }

    public void addNew(String s) {
        Integer level = map.getOrDefault(s, 0);
        level++;
        map.put(s, level);
    }

    public int level(String s) {
        return map.get(s);
    }
    public Set<String> keys() {
        return map.keySet();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }
}
