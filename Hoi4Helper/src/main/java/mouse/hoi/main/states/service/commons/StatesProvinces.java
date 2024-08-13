package mouse.hoi.main.states.service.commons;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.map.provinces.ProvinceDefinitions;
import mouse.hoi.main.map.provinces.ProvinceInfo;
import mouse.hoi.main.map.provinces.ProvinceLoader;
import mouse.hoi.main.map.provinces.ProvincesPreprocessor;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.loader.LoadedState;
import mouse.hoi.main.states.loader.StateSaverLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class StatesProvinces {
    private final StateSaverLoader stateSaverLoader;
    private final ProvinceLoader provinceLoader;
    private final ProvincesPreprocessor provincesPreprocessor;
    public void apply(BiConsumer<State, ProvinceDefinitions> consumer) {
        List<LoadedState> loadedStates = stateSaverLoader.loadAllStates();
        List<ProvinceInfo> provinces = provinceLoader.loadProvinces();
        ProvinceDefinitions definitions = provincesPreprocessor.preprocess(provinces);
        for (LoadedState state : loadedStates) {
            consumer.accept(state.state(), definitions);
        }
        stateSaverLoader.saveStates(loadedStates);
    }
}
