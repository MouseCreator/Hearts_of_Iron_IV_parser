package mouse.hoi.main.states.service.infrastructure;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.map.provinces.ProvinceDefinitions;
import mouse.hoi.main.states.data.Building;
import mouse.hoi.main.states.data.Buildings;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.service.commons.StateCommons;
import mouse.hoi.tools.random.RandomService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class InfrastructureGenerator {
    private final RandomService randomService;
    private final StateCommons stateCommons;
    public void assignInfrastructureLevel(State state, ProvinceDefinitions provinceDefinitions) {
        int level = chooseLevel(state, provinceDefinitions);
        Buildings buildings = state.historyOrInit().buildings();
        buildings.removeType("infrastructure");
        buildings.addBuilding("infrastructure", level);
    }

    private int chooseLevel(State state, ProvinceDefinitions provinceDefinitions) {
        int vpSum = stateCommons.vpSum(state);
        if (vpSum >= 30) {
            return 4;
        }
        if (vpSum >= 20) {
            return 3;
        }
        if (stateCommons.hasTerrain(state, "jungle", provinceDefinitions)) {
            return randomService.rand().nextBoolean() ? 1 : 2;
        }
        if (stateCommons.hasTerrain(state, "desert", provinceDefinitions)) {
            return randomService.rand().nextBoolean() ? 1 : 2;
        }

        return randomService.withChance(0.74) ? 3 : 2;

    }
}
