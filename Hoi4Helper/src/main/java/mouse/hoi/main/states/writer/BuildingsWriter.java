package mouse.hoi.main.states.writer;

import mouse.hoi.main.states.data.BuildingMap;
import mouse.hoi.main.states.data.Buildings;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BuildingsWriter implements DataWriter<Buildings> {
    @Override
    public Class<Buildings> forType() {
        return Buildings.class;
    }

    @Override
    public void write(SpecialWriter writer, Buildings buildings) {
        writer.beginObj();
        BuildingMap buildingMap = buildings.buildingsMap();
        Collection<Integer> provinceBuildings = buildings.usedProvinces();
        writeBuildings(writer, buildingMap);
        for (int province : provinceBuildings) {
            writer.write(province).write(" = ").beginObj();
            BuildingMap provinceBuilding = buildings.getProvinceBuilding(province).orElseThrow();
            writeBuildings(writer, provinceBuilding);
            writer.endObj();
        }
        writer.endObj();
    }

    private void writeBuildings(SpecialWriter writer,BuildingMap map) {
        for (String building : map.keys()) {
            writer.key(building).value(map.getLevel(building)).ln();
        }
    }
}
