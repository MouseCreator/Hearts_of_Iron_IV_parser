package mouse.hoi.main.states.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.Buildings;
import mouse.hoi.main.states.data.StateHistory;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateHistoryWriter implements DataWriter<StateHistory> {

    private final WriterSupport writerSupport;
    @Override
    public Class<StateHistory> forType() {
        return StateHistory.class;
    }

    @Override
    public DWData write(StateHistory history, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        b.key("owner").string(history::getOwner);
        b.listAll("add_core_of").strings(history::getCores);
        b.listAll("add_claim_by").strings(history::getClaims);
        b.listAll("victory_points").objects(history::getVictoryPointList, ObjectStyle.ONE_LINE);
        Buildings buildings = history.getBuildings();
        if (buildings != null && !buildings.isEmpty()) {
            b.key("buildings").objectRaw(buildings);
        }
        return b.get();
    }
}
