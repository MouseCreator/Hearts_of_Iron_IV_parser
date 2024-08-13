package mouse.hoi.main.states.writer;

import mouse.hoi.main.states.data.State;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.ListStyle;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;
import org.springframework.stereotype.Service;

@Service
public class StateWriter implements DataWriter<State> {
    @Override
    public Class<State> forType() {
        return State.class;
    }

    @Override
    public void write(SpecialWriter writer, State object) {
        writer.beginObj()
                .key("id").valueInt(object::getId).ln()
                .key("name").value(object::getName, StringStyle.QUOTED).ln()
                .key("manpower").valueInt(object::getManpower).ln().ln()
                .key("state_category").value(object::getCategory).ln().ln()
                .key("history").testValue(object::getStateHistory).printIfNotNullLn()
                .list("provinces", ListStyle.THREE_LINES).simple(object::getProvinces).ln()
                .key("buildings_max_level_factor").valueDouble(object::getBuildingsMaxLevelFactor).ln()
                .key("local_supplies").valueDouble(object::getLocalSupplies).ln().endObj();
    }
}
