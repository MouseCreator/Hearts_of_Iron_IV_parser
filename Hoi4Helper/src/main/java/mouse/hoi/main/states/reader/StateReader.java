package mouse.hoi.main.states.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.Resources;
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
        query.requireToken("id").integer().set(state::setId);
        query.requireToken("name").string().set(state::setName);
        query.onToken("manpower").integer().setOrDefault(state::setManpower, 0);
        query.requireToken("provinces").integerList().set(state::setProvinces);
        query.onToken("local_supplies").doublev().setOrDefault(state::setLocalSupplies, 0.0);
        query.onToken("state_category").string().setOrNull(state::setCategory);
        query.onToken("buildings_max_level_factor").doublev().setOrDefault(state::setBuildingsMaxLevelFactor, 1.0);
        query.onToken("history").object(StateHistory.class).setOrDefault(state::setStateHistory, new StateHistory());
        query.onToken("resources").object(Resources.class).setOrDefault(state::setResources, new Resources());
        return state;
    }
}
