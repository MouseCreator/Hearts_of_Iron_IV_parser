package mouse.hoi.main.states.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.data.StateWrapper;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateWrapperReader implements DataReader<StateWrapper> {

    private final DomQueryService queryService;
    @Override
    public Class<StateWrapper> forType() {
        return StateWrapper.class;
    }


    @Override
    public StateWrapper read(DomData domData) {
        StateWrapper stateWrapper = new StateWrapper();
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        query.requireToken("state").object(State.class).set(stateWrapper::setState);
        return stateWrapper;
    }
}
