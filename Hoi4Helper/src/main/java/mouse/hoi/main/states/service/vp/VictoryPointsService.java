package mouse.hoi.main.states.service.vp;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.map.provinces.*;
import mouse.hoi.main.states.loader.LoadedState;
import mouse.hoi.main.states.loader.StateSaverLoader;
import mouse.hoi.tools.context.AppService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VictoryPointsService implements AppService {

    private final StateSaverLoader stateSaverLoader;
    private final VictoryPointsGenerator victoryPointsGenerator;
    private final ProvinceLoader provinceLoader;
    private final ProvincesPreprocessor provincesPreprocessor;
    @Override
    public void start() {
        List<LoadedState> loadedStates = stateSaverLoader.loadAllStates();
        List< ProvinceInfo> provinces = provinceLoader.loadProvinces();
        ProvinceDefinitions definitions = provincesPreprocessor.preprocess(provinces);
        for (LoadedState state : loadedStates) {
            victoryPointsGenerator.generateVictoryPoints(state.state(), definitions);
        }
        stateSaverLoader.saveStates(loadedStates);
    }
}
