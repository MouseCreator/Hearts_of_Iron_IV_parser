package mouse.hoi.main.bookmark.service;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OwnerMapFactory {
    private final HistoricalDataFactory historicalDataFactory;
    public StateOwnersMap owners(Effects effects) {
        Map<Integer, StateHistoricalData> historicalDataMap = historicalDataFactory.generateHistorical(effects);
        Set<Integer> integers = historicalDataMap.keySet();
        StateOwnersMap map = new StateOwnersMap();
        for (int i : integers) {
            StateHistoricalData stateHistoricalData = historicalDataMap.get(i);
            map.put(stateHistoricalData.getOwner(), stateHistoricalData);
        }
        return map;
    }
}
