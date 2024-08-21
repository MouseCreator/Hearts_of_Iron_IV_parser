package mouse.hoi.main.states.service.resource;

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
public class ResourcesService implements AppService {

    private final StateSaverLoader stateSaverLoader;
    private final ProvinceLoader provinceLoader;
    private final ProvincesPreprocessor provincesPreprocessor;
    private final StateResourcesGenerator generator;
    @Override
    public void start() {
        List<LoadedState> loadedStates = stateSaverLoader.loadAllStates();
        List<ProvinceInfo> provinceInfos = provinceLoader.loadProvinces();
        ProvinceDefinitions definitions = provincesPreprocessor.preprocess(provinceInfos);
        generator.generateResources(loadedStates, definitions);
        stateSaverLoader.saveStates(loadedStates);
    }
}
