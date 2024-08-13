package mouse.hoi.main.states.service.categories;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.map.provinces.ProvinceDefinitions;
import mouse.hoi.main.map.provinces.ProvinceInfo;
import mouse.hoi.main.map.provinces.ProvinceLoader;
import mouse.hoi.main.map.provinces.ProvincesPreprocessor;
import mouse.hoi.main.states.loader.LoadedState;
import mouse.hoi.main.states.loader.StateSaverLoader;
import mouse.hoi.tools.context.AppService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateCategoryService implements AppService {
    private final StateSaverLoader stateSaverLoader;
    private final StateCategoryAssigner stateCategoryAssigner;
    private final ProvinceLoader provinceLoader;
    private final ProvincesPreprocessor provincesPreprocessor;
    @Override
    public void start() {
        List<LoadedState> loadedStates = stateSaverLoader.loadAllStates();
        List<ProvinceInfo> provinces = provinceLoader.loadProvinces();
        ProvinceDefinitions definitions = provincesPreprocessor.preprocess(provinces);
        for (LoadedState state : loadedStates) {
            stateCategoryAssigner.assignCategory(state.state(), definitions);
        }
        stateSaverLoader.saveStates(loadedStates);
    }
}
