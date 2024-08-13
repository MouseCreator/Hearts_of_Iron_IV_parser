package mouse.hoi.main.states.service.assign;

import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.data.StateHistory;
import org.springframework.stereotype.Service;

@Service
public class StateAssigner {

    public void assign(State state, String tag) {
        StateHistory stateHistory = state.getStateHistory();
        if (stateHistory == null) {
            stateHistory = new StateHistory();
            state.setStateHistory(stateHistory);
        }
        stateHistory.setOwner(tag);
    }
}
