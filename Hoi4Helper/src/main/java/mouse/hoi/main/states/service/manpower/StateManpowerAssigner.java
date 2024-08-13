package mouse.hoi.main.states.service.manpower;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.map.provinces.ProvinceDefinitions;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.service.commons.StateCommons;
import mouse.hoi.tools.random.RandomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateManpowerAssigner {

    private final RandomService randomService;
    private final StateCommons stateCommons;
    public void assignManpower(State state, ProvinceDefinitions provinceDefinitions) {
        int manpower = chooseManpower(state, provinceDefinitions);
        state.setManpower(manpower);
    }

    private int chooseManpower(State state, ProvinceDefinitions provinceDefinitions) {
        List<Integer> provinces = state.getProvinces();
        if (provinces.size() <= 2) {
            return randomService.rand().nextInt(8000, 15000);
        }
        int vpSum = stateCommons.vpSum(state);
        if (stateCommons.hasTerrain(state, "jungle", provinceDefinitions)) {
            return randomService.rand().nextInt(10000, 20000);
        }
        if (vpSum >= 45) {
            return randomService.rand().nextInt(2_000_000, 3_000_000);
        }
        if (vpSum >= 30) {
            return randomService.rand().nextInt(1_500_000, 2_000_000);
        }
        if (vpSum >= 20) {
            return randomService.rand().nextInt(1_000_000, 1_400_000);
        }
        if (vpSum >= 15) {
            return randomService.rand().nextInt(500_000, 800_000);
        }
        if (stateCommons.hasTerrain(state, "desert", provinceDefinitions)) {
            if (provinces.size() <= 5) {
                return randomService.rand().nextInt(5000, 9000);
            } else {
                return randomService.rand().nextInt(9000, 14000);
            }
        }
        return randomService.rand().nextInt(150_000, 350_000);

    }
}
