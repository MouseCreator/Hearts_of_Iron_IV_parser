package mouse.hoi.main.bookmark.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateOwnersMap {
    private final Map<String, List<StateHistoricalData>> map;

    public StateOwnersMap() {
        this.map = new HashMap<>();
    }

    public void put(String tag, StateHistoricalData data) {
        List<StateHistoricalData> v = map.computeIfAbsent(tag, k -> new ArrayList<>());
        v.add(data);
    }

    public List<StateHistoricalData> getByTag(String tag) {
        List<StateHistoricalData> stateHistoricalData = map.get(tag);
        if (stateHistoricalData == null) {
            throw new RuntimeException("Country has no states: " + tag);
        }
        return stateHistoricalData;
    }
}
