package mouse.hoi.main.states.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.Buildings;
import mouse.hoi.tools.parser.impl.categories.CategoryAssigner;
import mouse.hoi.tools.parser.impl.categories.CategoryMap;
import mouse.hoi.tools.parser.impl.categories.TokenCategory;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomKV;
import mouse.hoi.tools.parser.impl.dom.DomObject;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingsReader implements DataReader<Buildings> {

    private final CategoryAssigner categoryAssigner;
    @Override
    public Class<Buildings> forType() {
        return Buildings.class;
    }
    @Override
    public Buildings read(DomData domData) {
        CategoryMap categoryMap = categoryAssigner.mapCategory(domData.object());
        List<DomKV> buildingList = categoryMap.onCategory(TokenCategory.BUILDING);
        Buildings buildings = new Buildings();
        for (DomKV building : buildingList) {
            String type = building.key().val().stringValue();
            int level = building.value().simple().val().intValue();
            buildings.addBuilding(type, level);
        }
        List<DomKV> provinceBuildingList = categoryMap.onCategory(TokenCategory.NUMBER);
        for (DomKV provBuilding : provinceBuildingList) {
            createProvinceBuildings(buildings, provBuilding);
        }
        return buildings;
    }
    private void createProvinceBuildings(Buildings buildings, DomKV provBuilding) {
        int province = provBuilding.key().val().intValue();
        DomObject object = provBuilding.value().object();
        CategoryMap categoryMap = categoryAssigner.mapCategory(object);
        List<DomKV> provBuildList = categoryMap.onCategory(TokenCategory.BUILDING);
        for (DomKV domKV : provBuildList) {
            String type = domKV.key().val().stringValue();
            int level = domKV.value().simple().val().intValue();
            buildings.addProvinceBuilding(province, type, level);
        }
    }


}
