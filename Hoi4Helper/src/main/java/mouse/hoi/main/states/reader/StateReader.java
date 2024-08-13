package mouse.hoi.main.states.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.data.StateHistory;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StateReader implements DataReader<State> {

    private final Readers readers;
    @Override
    public Class<State> forType() {
        return State.class;
    }

    @Override
    public void onKeyValue(State state, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues().with(leftValue, rightValue)
                .onToken("id").setInteger(state::setId)
                .onToken("name").setString(state::setName)
                .onToken("manpower").setInteger(state::setManpower)
                .onToken("provinces").integerList().consume(state::setProvinces)
                .onToken("local_supplies").setDouble(state::setLocalSupplies)
                .onToken("state_category").setString(state::setCategory)
                .onToken("buildings_max_level_factor").setDouble(state::setBuildingsMaxLevelFactor)
                .onToken("history").mapBlock(b->readers.interpreters().read(StateHistory.class, b)).consume(state::setStateHistory)
                .orElseThrow();
    }
}
