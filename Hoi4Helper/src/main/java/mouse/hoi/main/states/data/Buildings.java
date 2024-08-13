package mouse.hoi.main.states.data;

import lombok.Data;
import mouse.hoi.tools.parser.impl.reader.Inits;

import java.util.ArrayList;
import java.util.List;

@Data
public class Buildings  {
    private List<Building> buildingList;
    private List<ProvinceBuilding> provinceBuildings;

    public Buildings() {
        buildingList = new ArrayList<>();
        provinceBuildings = new ArrayList<>();
    }

    public boolean isEmpty() {
        return buildingList.isEmpty() && provinceBuildings.isEmpty();
    }

    public void removeType(String typeTarget) {
        List<Building> toRemove = new ArrayList<>();
        for (Building building : buildingList) {
            if (building.getType().equals(typeTarget)) {
                toRemove.add(building);
            }
        }
        buildingList.removeAll(toRemove);

        List<ProvinceBuilding> toRemoveProv = new ArrayList<>();
        for (ProvinceBuilding building : provinceBuildings) {
            List<Building> rm = new ArrayList<>();
            for (Building provB : building.getBuildingList()) {
                if (provB.getType().equals(typeTarget)) {
                    rm.add(provB);
                }
            }
            building.getBuildingList().removeAll(rm);
            if (building.getBuildingList().isEmpty()) {
                toRemoveProv.add(building);
            }
        }
        buildingList.removeAll(toRemove);
        provinceBuildings.removeAll(toRemoveProv);
    }
}
