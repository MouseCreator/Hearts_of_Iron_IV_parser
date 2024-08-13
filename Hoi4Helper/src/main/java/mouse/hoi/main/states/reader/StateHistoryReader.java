package mouse.hoi.main.states.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.StateHistory;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateHistoryReader implements DataReader<StateHistory> {

    private final Readers readers;
    @Override
    public Class<StateHistory> forType() {
        return StateHistory.class;
    }

    @Override
    public void onKeyValue(StateHistory history, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues().with(leftValue, rightValue)
                .onToken("owner").setString(history::setOwner);
    }
}
