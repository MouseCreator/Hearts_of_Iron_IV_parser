package mouse.hoi.main.states.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.State;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ListStyle;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import mouse.hoi.tools.utils.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateWriter implements DataWriter<State> {

    private final WriterSupport writerSupport;
    @Override
    public Class<State> forType() {
        return State.class;
    }

    @Override
    public DWData write(State state, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        b.key("id").integer(state::getId);
        b.key("name").string(state::getName);
        b.key("manpower").integer(state::getManpower);
        b.key("state_category").string(state::getCategory);
        b.key("resources").object(state::getResources);
        NotNull.supply(state::getStateHistory, h -> b.key("history").objectRaw(h));
        b.key("provinces").integerList(state::getProvinces, ListStyle.THREE_LINES);
        b.key("buildings_max_level_factor").dbl(state::getBuildingsMaxLevelFactor);
        b.key("local_supplies").dbl(state::getLocalSupplies);
        return b.get();
    }
}
