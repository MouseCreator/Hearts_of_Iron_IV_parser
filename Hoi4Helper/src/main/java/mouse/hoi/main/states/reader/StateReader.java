package mouse.hoi.main.states.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.data.StateHistory;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StateReader implements DataReader<State> {


    private final DomQueryService queryService;
    @Override
    public Class<State> forType() {
        return State.class;
    }

    @Override
    public State read(DomData domData) {
        State state = new State();
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        query.onToken("id").integer().set(state::setId);
        query.onToken("name").string().set(state::setName);
        query.onToken("manpower").integer().set(state::setManpower);
        query.onToken("provinces").integerList().set(state::setProvinces);
        query.onToken("local_supplies").doublev().set(state::setLocalSupplies);
        query.onToken("state_category").string().set(state::setCategory);
        query.onToken("buildings_max_level_factor").doublev().set(state::setBuildingsMaxLevelFactor);
        query.onToken("history").object(StateHistory.class).set(state::setStateHistory);
        return state;
    }
}
