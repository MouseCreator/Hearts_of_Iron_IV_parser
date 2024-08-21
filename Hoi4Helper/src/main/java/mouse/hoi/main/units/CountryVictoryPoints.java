package mouse.hoi.main.units;

import mouse.hoi.main.states.data.VictoryPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryVictoryPoints {
    private final Map<String, List<VictoryPoint>> map;

    public CountryVictoryPoints() {
        map = new HashMap<>();
    }

    public List<VictoryPoint> victoryPoints(String country) {
        return map.getOrDefault(country, new ArrayList<>());
    }
    public void setVictoryPoints(String country, List<VictoryPoint> victoryPoints) {
        map.put(country, victoryPoints);
    }
    public void addVictoryPoint(String country, VictoryPoint victoryPoint) {
        List<VictoryPoint> victoryPoints = map.computeIfAbsent(country, k -> new ArrayList<>());
        victoryPoints.add(victoryPoint);
    }
}
