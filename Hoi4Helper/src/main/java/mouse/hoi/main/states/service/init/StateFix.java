package mouse.hoi.main.states.service.init;

import mouse.hoi.main.states.data.State;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateFix {
    public void fixStates(List<State> states) {
        for (State state : states) {
            fixState(state);
        }
    }

    public void fixState(State state) {
        if (state.getManpower()==0) {
            state.setManpower(1);
        }
        if (state.getCategory()==null) {
            state.setCategory("rural");
        }
    }
}
