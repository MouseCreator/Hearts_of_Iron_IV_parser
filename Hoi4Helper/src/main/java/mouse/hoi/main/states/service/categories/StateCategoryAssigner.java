package mouse.hoi.main.states.service.categories;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.map.provinces.ProvinceDefinitions;
import mouse.hoi.main.map.provinces.ProvinceInfo;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.data.StateHistory;
import mouse.hoi.main.states.data.VictoryPoint;
import mouse.hoi.tools.random.RandomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateCategoryAssigner {

    private final RandomService randomService;

    public void assignCategory(State state, ProvinceDefinitions provinceDefinitions) {
        String category = chooseCategory(state, provinceDefinitions);
        state.setCategory(category);
    }

    private String chooseCategory(State state, ProvinceDefinitions provinceDefinitions) {
        List<Integer> provinces = state.getProvinces();
        if (provinces.size() < 3) {
            return "small_island";
        }
        int victoryPointSum = vpSum(state);
        if (victoryPointSum >= 45) {
            return "megalopolis";
        }
        if (victoryPointSum >= 30) {
            return "large_city";
        }
        if (victoryPointSum >= 20) {
            return randomService.rand().nextBoolean() ? "city" : "large_town";
        }
        if (provinces.size() < 3) {
            return "rural";
        }
        if (hasTerrain(state, "jungle", provinceDefinitions)) {
            return "rural";
        }
        if (hasTerrain(state, "desert", provinceDefinitions)) {
            return "pastoral";
        }
        if (victoryPointSum >= 10) {
            return "town";
        }
        if (hasTerrain(state, "marsh", provinceDefinitions)) {
            return "rural";
        }
        return randomService.rand().nextBoolean() ? "town" : "rural";
    }

    private boolean hasTerrain(State state, String terrain, ProvinceDefinitions provinceDefinitions) {
        List<Integer> provinces = state.getProvinces();
        for (int prov : provinces) {
            ProvinceInfo provinceById = provinceDefinitions.getProvinceById(prov);
            if (provinceById.getTerrain().equals(terrain)) {
                return true;
            }
        }
        return false;
    }

    private int vpSum(State state) {
        StateHistory stateHistory = state.historyOrInit();
        return stateHistory.getVictoryPointList().stream().mapToInt(VictoryPoint::getPoints).sum();
    }
}
