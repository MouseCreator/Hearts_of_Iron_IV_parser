package mouse.hoi.main.states.service.manpower;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.service.commons.StatesProvinces;
import mouse.hoi.tools.context.AppService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StateManpowerService implements AppService {
    private final StatesProvinces statesProvinces;
    private final StateManpowerAssigner stateManpowerAssigner;
    @Override
    public void start() {
        statesProvinces.apply(stateManpowerAssigner::assignManpower);
    }
}
