package mouse.hoi.main.states.service.naval;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.loader.LoadedState;
import mouse.hoi.main.states.loader.StateSaverLoader;
import mouse.hoi.tools.context.AppService;
import mouse.hoi.tools.files.FileManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NavalBaseService implements AppService {
    private final StateSaverLoader stateSaverLoader;
    private final FileManager fileManager;
    private final NavalBaseGenerator navalBaseGenerator;
    @Override
    public void start() {
        List<LoadedState> loadedStates = stateSaverLoader.loadAllStates();
        String filename = "src/main/resources/states/ports.input";
        Map<Integer, Integer> map = loadNavalBases(filename);
        for (LoadedState state : loadedStates) {
            navalBaseGenerator.addNavalBases(state.state(), map);
        }
        stateSaverLoader.saveStates(loadedStates);
    }

    private Map<Integer, Integer> loadNavalBases(String filename) {
        List<String> strings = fileManager.readLines(filename);
        Map<Integer, Integer> map = new HashMap<>();
        for (String line : strings) {
            String trimmed = line.trim();
            if (trimmed.isEmpty())
                continue;
            String[] split = trimmed.split(" +", 2);
            int province = Integer.parseInt(split[0]);
            int level = Integer.parseInt(split[1]);
            map.put(province, level);
        }
        return map;
    }
}
