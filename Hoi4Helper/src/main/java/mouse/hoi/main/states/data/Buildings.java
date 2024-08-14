package mouse.hoi.main.states.data;

import lombok.Data;

import java.util.*;


public class Buildings  {
    private final BuildingMap buildingMap;
    private final Map<Integer, BuildingMap> provinceBuildings;

    public Buildings() {
        buildingMap = new BuildingMap();
        provinceBuildings = new HashMap<>();
    }

    public boolean isEmpty() {
        return buildingMap.isEmpty() && provinceBuildings.isEmpty();
    }

    public void removeType(String typeTarget) {
        buildingMap.remove(typeTarget);
        for (BuildingMap provinceMap : provinceBuildings.values()) {
            provinceMap.remove(typeTarget);
        }
    }

    public Optional<BuildingMap> getProvinceBuilding(int prov) {
        BuildingMap buildingMap = provinceBuildings.get(prov);
        return Optional.ofNullable(buildingMap);
    }
    public void addBuilding(String type, int level) {
        buildingMap.put(type, level);
    }
    public void addProvinceBuilding(int prov, String type, int level) {
        BuildingMap provMap = provinceBuildings.computeIfAbsent(prov, k -> new BuildingMap());
        provMap.put(type, level);
    }
    public void removeProvinceBuilding(int prov, String type) {
        BuildingMap provMap = provinceBuildings.get(prov);
        if (provMap == null) {
            return;
        }
        provMap.remove(type);
    }

    public BuildingMap buildingsMap() {
        return buildingMap;
    }

    public Collection<Integer> usedProvinces() {
        return provinceBuildings.keySet();
    }
}
