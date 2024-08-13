package mouse.hoi.main.states.writer;

import mouse.hoi.main.states.data.Building;
import mouse.hoi.main.states.data.Buildings;
import mouse.hoi.main.states.data.ProvinceBuilding;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BuildingsWriter implements DataWriter<Buildings> {
    @Override
    public Class<Buildings> forType() {
        return Buildings.class;
    }

    @Override
    public void write(SpecialWriter writer, Buildings buildings) {
        writer.beginObj();
        List<Building> buildingList = buildings.getBuildingList();
        List<ProvinceBuilding> provinceBuildings = buildings.getProvinceBuildings();
        writeBuildings(writer, buildingList);
        for (ProvinceBuilding provinceBuilding : provinceBuildings) {
            writer.write(provinceBuilding.getProvince()).write(" = ").beginObj();
            List<Building> provB = provinceBuilding.getBuildingList();
            writeBuildings(writer, provB);
            writer.endObj();
        }
        writer.endObj();
    }

    private void writeBuildings(SpecialWriter writer, List<Building> buildingList) {
        for (Building building : buildingList) {
            writer.key(building.getType()).value(building.getLevel()).ln();
        }
    }
}
