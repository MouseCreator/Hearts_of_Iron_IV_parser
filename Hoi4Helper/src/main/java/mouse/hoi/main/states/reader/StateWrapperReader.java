package mouse.hoi.main.states.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.State;
import mouse.hoi.main.states.data.StateWrapper;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateWrapperReader implements DataReader<StateWrapper> {

    private final Readers readers;
    @Override
    public Class<StateWrapper> forType() {
        return StateWrapper.class;
    }

    @Override
    public void onKeyValue(StateWrapper object, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues()
                .with(leftValue, rightValue).onToken("state").mapBlock(b -> readers.interpreters().read(State.class, b)).consume(object::setState)
                .orElseThrow();
    }
}
