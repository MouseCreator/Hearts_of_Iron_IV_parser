package mouse.hoi.main.states.service.resource;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.map.provinces.ProvinceDefinitions;
import mouse.hoi.main.map.provinces.ProvinceInfo;
import mouse.hoi.main.states.data.Resources;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.data.VictoryPoint;
import mouse.hoi.main.states.loader.LoadedState;
import mouse.hoi.tools.random.RandomService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StateResourcesGenerator {

    private final RandomService randomService;
    public void generateResources(List<LoadedState> loadedStateList, ProvinceDefinitions definitions) {
        for (LoadedState loadedState : loadedStateList) {
            generateResources(loadedState.state(), definitions);
        }
    }

    private void generateResources(State state, ProvinceDefinitions definitions) {
        state.getResources().clear();
        boolean isDesert = isDesert(state, definitions);
        boolean isMountain = isMountain(state, definitions);
        boolean isMarsh = isMarch(state, definitions);
        boolean isJungle = isJungle(state, definitions);
        int victoryPointsSum = vpSum(state);

        boolean isPlains = !isDesert && !isMountain && !isMarsh && !isJungle;

        Set<String> likelyResources = new HashSet<>();

        if (isDesert) {
            doWithChance(0.75, () -> likelyResources.add("oil"));
            doWithChance(0.12, () -> likelyResources.add("steel"));
            doWithChance(0.05, () -> likelyResources.add("aluminium"));
            doWithChance(0.02, () -> likelyResources.add("magic_element"));
        }
        if (isMountain) {
            doWithChance(0.4, () -> likelyResources.add("steel"));
            doWithChance(0.12, () -> likelyResources.add("tungsten"));
            doWithChance(0.1, () -> likelyResources.add("chromium"));
            doWithChance(0.11, () -> likelyResources.add("aluminium"));
        }
        if (isMarsh) {
            doWithChance(0.2, () -> likelyResources.add("magic_element"));
            doWithChance(0.05, () -> likelyResources.add("tungsten"));
            doWithChance(0.06, () -> likelyResources.add("chromium"));
        }
        if (isJungle) {
            doWithChance(0.85, () -> likelyResources.add("rubber"));
            doWithChance(0.7, () -> likelyResources.add("oil"));
        }
        if (isPlains) {
            doWithChance(0.05, () -> likelyResources.add("steel"));
            doWithChance(0.05, () -> likelyResources.add("tungsten"));
            doWithChance(0.05, () -> likelyResources.add("chromium"));
            doWithChance(0.05, () -> likelyResources.add("aluminium"));
            doWithChance(0.05, () -> likelyResources.add("oil"));
            doWithChance(0.05, () -> likelyResources.add("magic_element"));
        }
        if (victoryPointsSum > 20) {
            doWithChance(0.15, () -> likelyResources.add("steel"));
            doWithChance(0.05, () -> likelyResources.add("tungsten"));
            doWithChance(0.05, () -> likelyResources.add("chromium"));
            doWithChance(0.05, () -> likelyResources.add("magic_element"));
            doWithChance(0.1, () -> likelyResources.add("aluminium"));
        } else if (victoryPointsSum > 15) {
            doWithChance(0.05, () -> likelyResources.add("steel"));
            doWithChance(0.04, () -> likelyResources.add("tungsten"));
            doWithChance(0.04, () -> likelyResources.add("chromium"));
            doWithChance(0.04, () -> likelyResources.add("aluminium"));
            doWithChance(0.06, () -> likelyResources.add("magic_element"));
        }

        Resources resources = state.getResources();
        for (String resource : likelyResources) {
            generateResourceAndPut(resources, resource);
        }
    }

    private void generateResourceAndPut(Resources resources, String resource) {
        int amount = randomAmountFor(resource);
        doWithChance(0.9, () -> resources.put(resource, amount));
    }

    private int randomAmountFor(String resource) {
        switch (resource) {
            case "steel" -> {
                double v = randomService.rand().nextDouble();
                if (v < 0.3) {
                    return randomService.rand().nextInt(2, 9);
                } else if (v < 0.82) {
                    return randomService.rand().nextInt(15, 55);
                } else {
                    return randomService.rand().nextInt(100, 130);
                }
            }
            case "rubber" -> {
                return randomService.rand().nextInt(40, 140);
            }
            case "oil" -> {
                double v = randomService.rand().nextDouble();
                if (v < 0.2) {
                    return randomService.rand().nextInt(50, 100);
                }
                return randomService.rand().nextInt(8, 45);
            }
            case "magic_element" -> {
                return randomService.rand().nextInt(3, 30);
            }
            case "aluminium" -> {
                return randomService.rand().nextInt(4, 40);
            }
            case "chromium", "tungsten" -> {
                return randomService.rand().nextInt(2, 26);
            }
            default -> {
                throw new RuntimeException("Unknown resource: " + resource);
            }
        }
    }

    private boolean isJungle(State state, ProvinceDefinitions definitions) {
        return hasTerrain(state, definitions, "jungle");
    }

    private void doWithChance(double chance, Runnable r) {
        double v = randomService.rand().nextDouble();
        if (v < chance) {
            r.run();
        }
    }

    private boolean isMarch(State state, ProvinceDefinitions definitions) {
        return hasTerrain(state, definitions, "marsh");
    }

    private boolean isForest(State state, ProvinceDefinitions definitions) {
        return hasTerrain(state, definitions, "forest");
    }

    private boolean isMountain(State state, ProvinceDefinitions definitions) {
        return hasTerrain(state, definitions, "mountain");
    }

    private boolean hasTerrain(State state, ProvinceDefinitions definitions, String terrain) {
        List<Integer> provinces = state.getProvinces();
        for (int i : provinces) {
            ProvinceInfo info = definitions.getProvinceById(i);
            if (info.getTerrain().equals(terrain)) {
                return true;
            }
        }
        return false;
    }

    private int vpSum(State state) {
        List<VictoryPoint> victoryPointList = state.historyOrInit().getVictoryPointList();
        int sum = 0;
        for (VictoryPoint vp : victoryPointList) {
            sum += vp.getPoints();
        }
        return sum;
    }

    private boolean isDesert(State state, ProvinceDefinitions definitions) {
        return hasTerrain(state, definitions, "desert");
    }
}
