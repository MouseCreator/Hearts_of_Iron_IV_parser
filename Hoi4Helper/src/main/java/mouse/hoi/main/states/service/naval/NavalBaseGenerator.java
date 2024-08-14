package mouse.hoi.main.states.service.naval;

import mouse.hoi.main.states.data.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NavalBaseGenerator {
    public void addNavalBases(State state, Map<Integer, Integer> bases) {
        for (int prov : state.getProvinces()) {
            Integer level = bases.get(prov);
            if (level == null) {
                continue;
            }
            Buildings buildings = state.historyOrInit().buildings();
            buildings.addProvinceBuilding(prov, "naval_base", level);
        }
    }
}
