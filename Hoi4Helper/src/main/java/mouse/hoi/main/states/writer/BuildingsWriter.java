package mouse.hoi.main.states.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.BuildingMap;
import mouse.hoi.main.states.data.Buildings;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BuildingsWriter implements DataWriter<Buildings> {

    private final WriterSupport writerSupport;
    @Override
    public Class<Buildings> forType() {
        return Buildings.class;
    }

    @Override
    public DWData write(Buildings buildings, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        BuildingMap buildingMap = buildings.buildingsMap();
        Collection<Integer> usedProvinces = buildings.usedProvinces();
        writeBuildings(b, buildingMap);
        for (int prov : usedProvinces) {
            DWObjectBuilder p = writerSupport.build(style);
            BuildingMap provMap = buildings.getProvinceBuilding(prov).orElseThrow();
            writeBuildings(p, provMap);
            DWData dwData = p.get();
            b.key(prov).value(dwData);
        }
        return b.get();
    }

    private void writeBuildings(DWObjectBuilder b, BuildingMap buildingMap) {
        for (String building : buildingMap.keys()) {
            int level = buildingMap.getLevel(building);
            if (level != 0) {
                b.key(building).integer(level);
            }
        }
    }
}
