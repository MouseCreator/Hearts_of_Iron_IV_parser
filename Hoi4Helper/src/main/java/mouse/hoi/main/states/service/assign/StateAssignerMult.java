package mouse.hoi.main.states.service.assign;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.loader.LoadedState;
import mouse.hoi.main.states.loader.StateSaverLoader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateAssignerMult {
    private final StateSaverLoader stateSaverLoader;
    private final StateAssigner assigner;
    public void assignAllTo(String folder, String tag) {
        List<LoadedState> loadedStates = stateSaverLoader.loadAllStates(folder);
        for (LoadedState loadedState : loadedStates) {
            State state = loadedState.state();
            assigner.assign(state, tag);
        }
        stateSaverLoader.saveStates(loadedStates);
    }
}
