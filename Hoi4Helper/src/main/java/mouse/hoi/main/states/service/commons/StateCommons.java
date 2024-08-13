package mouse.hoi.main.states.service.commons;

import mouse.hoi.main.map.provinces.ProvinceDefinitions;
import mouse.hoi.main.map.provinces.ProvinceInfo;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.data.StateHistory;
import mouse.hoi.main.states.data.VictoryPoint;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StateCommons {
    public boolean hasTerrain(State state, String terrain, ProvinceDefinitions provinceDefinitions) {
        List<Integer> provinces = state.getProvinces();
        for (int prov : provinces) {
            ProvinceInfo provinceById = provinceDefinitions.getProvinceById(prov);
            if (provinceById.getTerrain().equals(terrain)) {
                return true;
            }
        }
        return false;
    }

    public int vpSum(State state) {
        StateHistory stateHistory = state.historyOrInit();
        return stateHistory.getVictoryPointList().stream().mapToInt(VictoryPoint::getPoints).sum();
    }
}
