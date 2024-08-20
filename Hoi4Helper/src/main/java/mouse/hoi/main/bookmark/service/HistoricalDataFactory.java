package mouse.hoi.main.bookmark.service;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.common.data.effect.Effect;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.main.map.provinces.*;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.loader.LoadedState;
import mouse.hoi.main.states.loader.StateSaverLoader;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoricalDataFactory {
    private final StateSaverLoader stateSaverLoader;
    private final ProvinceLoader provinceLoader;
    private final ProvincesPreprocessor provincesPreprocessor;
    public Map<Integer, StateHistoricalData> generateHistorical(Effects effects) {
        List<LoadedState> stateList = stateSaverLoader.loadAllStates();
        List<ProvinceInfo> provinceInfos = provinceLoader.loadProvinces();
        ProvinceDefinitions provinceDefinitions = provincesPreprocessor.preprocess(provinceInfos);
        Map<Integer, StateHistoricalData> historicalDataMap = new HashMap<>();
        for (LoadedState loadedState : stateList) {
            State state = loadedState.state();
            int id = state.getId();
            StateHistoricalData historicalData = generateHistoricalData(state, effects, provinceDefinitions);
            historicalDataMap.put(id, historicalData);
        }
        return historicalDataMap;
    }

    private StateHistoricalData generateHistoricalData(State state, Effects effects, ProvinceDefinitions provinceDefinitions) {
        StateHistoricalData stateHistoricalData = new StateHistoricalData();
        stateHistoricalData.setOrigin(state);
        setCoastal(state, provinceDefinitions, stateHistoricalData);
        int id = state.getId();
        stateHistoricalData.setStateId(id);
        Optional<Effects> subEffect = effects.getSubEffect(String.valueOf(id));
        if (subEffect.isEmpty()) {
            stateHistoricalData.setOwner("");
        } else {
            String owner = "";
            Effects subs = subEffect.get();
            List<Effect> transferStateTo = subs.simpleEffects().getEffects("transfer_state_to");
            if (!transferStateTo.isEmpty()) {
                owner = transferStateTo.getLast().stringValue();
            }
            stateHistoricalData.setOwner(owner);
            List<Effect> addCoreOf = subs.simpleEffects().getEffects("add_core_of");
            for (Effect core : addCoreOf) {
                String cored = core.stringValue();
                stateHistoricalData.getCores().add(cored);
            }
            List<Effect> addClaims = subs.simpleEffects().getEffects("add_claim_by");
            for (Effect claim : addClaims) {
                String claimed = claim.stringValue();
                stateHistoricalData.getClaims().add(claimed);
            }
        }
        return stateHistoricalData;
    }

    private static void setCoastal(State state, ProvinceDefinitions provinceDefinitions, StateHistoricalData stateHistoricalData) {
        List<Integer> provinces = state.getProvinces();
        for (int i : provinces) {
            boolean coastal = provinceDefinitions.getProvinceById(i).isCoastal();
            if (coastal) {
                stateHistoricalData.setCoastal(true);
                return;
            }
        }
    }
}
