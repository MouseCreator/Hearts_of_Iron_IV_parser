package mouse.hoi.main.units;

import mouse.hoi.main.common.data.effect.effects.TransferStateToEffect;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.data.VictoryPoint;
import mouse.hoi.main.states.loader.LoadedState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryVictoryPointsFactory {
    public CountryVictoryPoints forCountry(Effects effects, List<LoadedState> loadedStateList) {
        CountryVictoryPoints countryVictoryPoints = new CountryVictoryPoints();
        for (LoadedState loadedState : loadedStateList) {
            State state = loadedState.state();
            int id = state.getId();
            String tId = String.valueOf(id);
            Optional<Effects> subEffect = effects.getSubEffect(tId);
            if (subEffect.isEmpty()) {
                continue;
            }
            Effects stateEffects = subEffect.get();
            List<TransferStateToEffect> transfer = stateEffects.ofType(TransferStateToEffect.class);
            if (transfer.isEmpty()) {
                continue;
            }
            TransferStateToEffect ownerEffect = transfer.getFirst();
            String owner = ownerEffect.stringValue().toUpperCase();
            List<VictoryPoint> vps = state.historyOrInit().getVictoryPointList();
            vps.forEach(v -> countryVictoryPoints.addVictoryPoint(owner, v));
        }
        return countryVictoryPoints;
    }
}
