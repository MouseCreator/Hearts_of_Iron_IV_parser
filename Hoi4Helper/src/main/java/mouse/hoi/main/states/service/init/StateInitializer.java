package mouse.hoi.main.states.service.init;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.loader.LoadedState;
import mouse.hoi.main.states.loader.StateSaverLoader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateInitializer {

    private final StateSaverLoader stateSaverLoader;
    private final StateFix stateFix;
    public void initStates(String folder) {
        List<LoadedState> loadedStates = stateSaverLoader.loadAllStates(folder);
        for (LoadedState loadedState : loadedStates) {
            State state = loadedState.state();
            stateFix.fixState(state);
        }
        stateSaverLoader.saveStates(loadedStates);
    }
}
