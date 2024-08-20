package mouse.hoi.main.bookmark.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookmarkGenerateBuildings {
    public Map<Integer, PreparedBuildings> generateBuildings(StateOwnersMap map, BuildingInputs inputs) {
        Set<String> keys = inputs.keys();
        Map<Integer, PreparedBuildings> buildingsMap = new HashMap<>();
        for (String key : keys) {
            BuildingDef def = inputs.getDef(key);
            List<StateHistoricalData> statesOwnedByCountry = map.getByTag(key);
            List<StateHistoricalData> onlyWithCores = filterStates(statesOwnedByCountry);
            generateBuildingsIn(def, onlyWithCores, buildingsMap);
        }
        return buildingsMap;
    }

    private void generateBuildingsIn(BuildingDef def, List<StateHistoricalData> onlyWithCores, Map<Integer, PreparedBuildings> toFill) {
        List<StateBuildings> stateBuildings = createStateBuildings(onlyWithCores);
        int docs = def.getDocs();
        List<Double> weightsCoastal = weightsCoastal(stateBuildings);
        for (int i = 0; i < docs; i++) {
            int index = getRandomElement(weightsCoastal);
            StateBuildings buildings = stateBuildings.get(index);
            weightsCoastal.set(index, weightsCoastal.get(index)-1);
            buildings.freeSlots -= 1;
            buildings.preparedBuilding.addNew("dockyard");
        }
        int civs = def.getCivs();
        for (int i = 0; i < civs; i++) {
            List<Double> weights = weights(stateBuildings);
            int index = getRandomElement(weights);
            weights.set(index, weights.get(index)-1);
            StateBuildings buildings = stateBuildings.get(index);
            buildings.freeSlots -= 1;
            buildings.preparedBuilding.addNew("industrial_complex");
        }
        int mils = def.getMils();
        for (int i = 0; i < mils; i++) {
            List<Double> weights = weights(stateBuildings);
            int index = getRandomElement(weights);
            weights.set(index, weights.get(index)-1);
            StateBuildings buildings = stateBuildings.get(index);
            buildings.freeSlots -= 1;
            buildings.preparedBuilding.addNew("arms_factory");
        }
        for (StateBuildings buildings : stateBuildings) {
            toFill.put(buildings.stateId, buildings.preparedBuilding);
        }
    }

    private List<Double> weightsCoastal(List<StateBuildings> stateBuildings) {
        List<Double> doubles = new ArrayList<>();
        for (StateBuildings b : stateBuildings) {
            if (b.freeSlots < 2) {
                doubles.add(0.0);
                continue;
            }
            if (b.coastal) {
                doubles.add((double) b.freeSlots);
                continue;
            }
            doubles.add(0.0);
        }
        return doubles;
    }

    private List<Double> weights(List<StateBuildings> stateBuildings) {
        List<Double> doubles = new ArrayList<>();
        for (StateBuildings b : stateBuildings) {
            if (b.freeSlots < 2) {
                doubles.add(0.0);
                continue;
            }
            doubles.add((double) b.originalSlots * b.originalSlots);
        }
        return doubles;
    }

    private List<StateBuildings> createStateBuildings(List<StateHistoricalData> onlyWithCores) {
        List<StateBuildings> result = new ArrayList<>();
        for (StateHistoricalData data : onlyWithCores) {
            String category = data.getOrigin().getCategory();
            StateBuildings buildings = new StateBuildings();
            buildings.stateId = data.getStateId();
            buildings.preparedBuilding = new PreparedBuildings();
            buildings.freeSlots = switch (category) {
                case "city" -> 6;
                case "town" -> 4;
                case "rural" -> 2;
                case "pastoral" -> 1;
                case "megalopolis" -> 12;
                case "metropolis" -> 10;
                case "large_city" -> 8;
                case "large_town" -> 5;
                default -> 0;
            };
            buildings.originalSlots = buildings.freeSlots;
            buildings.coastal = data.isCoastal();
            result.add(buildings);
        }
        return result;
    }

    private static class StateBuildings {
        private int stateId;
        private boolean coastal;
        private int freeSlots;
        private int originalSlots;
        private PreparedBuildings preparedBuilding;
    }

    private int getRandomElement(List<Double> list) {
        List<Double> cumulativeWeights = new ArrayList<>();
        double cumulativeSum = 0;

        for (double weight : list) {
            cumulativeSum += weight;
            cumulativeWeights.add(cumulativeSum);
        }
        double randomValue = Math.random() * cumulativeSum;
        for (int i = 0; i < cumulativeWeights.size(); i++) {
            if (randomValue <= cumulativeWeights.get(i)) {
                return i;
            }
        }

        return list.size() - 1;
    }

    private List<StateHistoricalData> filterStates(List<StateHistoricalData> statesOwnedByCountry) {
        List<StateHistoricalData> result = new ArrayList<>();
        for (StateHistoricalData stateHistoricalData : statesOwnedByCountry) {
            String owner = stateHistoricalData.getOwner();
            for (String core : stateHistoricalData.getCores()) {
                if (owner.equals(core)) {
                    result.add(stateHistoricalData);
                    break;
                }
            }
        }
        return result;
    }
}
