package mouse.hoi.main.states.service.vp;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.map.provinces.ProvinceDefinitions;
import mouse.hoi.main.map.provinces.ProvinceInfo;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.data.StateHistory;
import mouse.hoi.main.states.data.VictoryPoint;
import mouse.hoi.tools.random.RandomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class VictoryPointsGenerator {
    private final RandomService randomService;
    public void generateVictoryPoints(State state, ProvinceDefinitions definitions) {
        List<Integer> provinces = state.getProvinces();
        int size = provinces.size();
        int expectedVictoryPoints = getExpected(size);
        List<Integer> cityProvs = getCityProvs(definitions, provinces);
        int cityPoints = cityProvs.size();
        int totalVP = Math.max(cityPoints, expectedVictoryPoints);
        int nonCityPoints = totalVP - cityPoints;
        List<Integer> ruralProv = new ArrayList<>(provinces);
        ruralProv.removeAll(cityProvs);
        List<VictoryPoint> victoryPoints = new ArrayList<>();
        for (int id : cityProvs) {
            VictoryPoint victoryPoint = createCityVP(id);
            victoryPoints.add(victoryPoint);
        }
        List<Integer> ruralChosen = chooseProvinces(nonCityPoints, ruralProv);
        for (int id : ruralChosen) {
            VictoryPoint victoryPoint = createRuralVP(id);
            victoryPoints.add(victoryPoint);
        }
        StateHistory stateHistory = state.historyOrInit();
        stateHistory.setVictoryPointList(victoryPoints);
    }

    private List<Integer> chooseProvinces(int nonCityPoints, List<Integer> ruralProv) {
        List<Integer> list = new ArrayList<>(ruralProv);
        Collections.shuffle(list);
        return list.subList(0, nonCityPoints);
    }

    private VictoryPoint createCityVP(int id) {
        final int[] possiblePoints = { 3, 5, 5, 5, 5, 10, 10, 15 };
        int r = randomService.rand().nextInt(possiblePoints.length);
        int v = possiblePoints[r];
        return new VictoryPoint(id, v);
    }

    private VictoryPoint createRuralVP(int id) {
        final int[] possiblePoints = { 1, 1, 3 };
        int r = randomService.rand().nextInt(possiblePoints.length);
        int v = possiblePoints[r];
        return new VictoryPoint(id, v);
    }

    private List<Integer> getCityProvs(ProvinceDefinitions def, List<Integer> provinces) {
        List<Integer> result = new ArrayList<>();
        for (int prov : provinces) {
            ProvinceInfo provinceById = def.getProvinceById(prov);
            if (provinceById.getTerrain().equals("urban")) {
                result.add(prov);
            }
        }
        return result;
    }

    private int getExpected(int size) {
        if (size < 4) {
            return randomService.rand().nextBoolean() ? 1 : 0;
        }
        if (size < 8) {
            return randomService.rand().nextInt(1, 4);
        }
        return randomService.rand().nextInt(2, 5);
    }
}
