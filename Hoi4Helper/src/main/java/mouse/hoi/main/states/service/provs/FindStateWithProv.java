package mouse.hoi.main.states.service.provs;

import mouse.hoi.main.states.loader.LoadedState;
import mouse.hoi.main.states.loader.StateSaverLoader;
import mouse.hoi.tools.context.AppService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FindStateWithProv implements AppService {

    private final StateSaverLoader stateSaverLoader;

    public FindStateWithProv(StateSaverLoader stateSaverLoader) {
        this.stateSaverLoader = stateSaverLoader;
    }

    @Override
    public void start() {
        List<LoadedState> loadedStates = stateSaverLoader.loadAllStates();
        int[] find = { 2105, 5017, 228 };
        Set<Integer> set = new HashSet<>();
        for (int i : find) {
            set.add(i);
        }
        for (LoadedState state : loadedStates) {
            List<Integer> provinces = state.state().getProvinces();
            for (int prov : provinces) {
                if (set.contains(prov))
                    System.out.println( state.state().getId() + " contains "  + prov);
            }
        }
    }
}
