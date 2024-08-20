package mouse.hoi.main.states.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.Buildings;
import mouse.hoi.main.states.data.StateHistory;
import mouse.hoi.main.states.data.VictoryPoint;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateHistoryReader implements DataReader<StateHistory> {

    private final InterpreterManager interpreterManager;


    private final DomQueryService queryService;
    @Override
    public Class<StateHistory> forType() {
        return StateHistory.class;
    }

    @Override
    public StateHistory read(DomData domData) {
        StateHistory history = new StateHistory();
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        query.onToken("owner").string().setOrNull(history::setOwner);
        List<DomData> victoryPoints = query.onToken("victory_points").list();
        for (DomData vp : victoryPoints) {
            VictoryPoint victoryPoint = interpreterManager.createObject(vp, VictoryPoint.class);
            history.getVictoryPointList().add(victoryPoint);
        }
        query.onToken("buildings").object(Buildings.class).setOrSupply(history::setBuildings, Buildings::new);
        query.onToken("add_core_of").string().push(history::getCores);
        query.onToken("add_claim_by").string().push(history::getClaims);
        return history;
    }
}
