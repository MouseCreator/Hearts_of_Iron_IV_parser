package mouse.hoi.main.states.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Resources {
    private final Map<String, Integer> map;

    public Resources() {
        map = new HashMap<>();
    }

    public void put(String type, int amount) {
        map.put(type, amount);
    }
    public void add(String type, int toAdd) {
        int amount = getAmount(type);
        map.put(type, amount + toAdd);
    }

    public Set<String> keys() {
        return map.keySet();
    }
    public int getAmount(String type) {
        return map.getOrDefault(type, 0);
    }

    public void clear() {
        map.clear();
    }
}
