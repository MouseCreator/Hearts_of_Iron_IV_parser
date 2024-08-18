package mouse.hoi.main.states.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.Buildings;
import mouse.hoi.main.states.data.StateHistory;
import mouse.hoi.main.states.data.VictoryPoint;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateHistoryWriter implements DataWriter<StateHistory> {
    @Override
    public Class<StateHistory> forType() {
        return StateHistory.class;
    }

    @Override
    public void write(SpecialWriter writer, StateHistory history) {
        writer.beginObj();
        writer.key("owner").value(history::getOwner).ln();
        for (VictoryPoint victoryPoint : history.getVictoryPointList()) {
            writer.key("victory_points").object(victoryPoint);
        }
        Buildings buildings = history.getBuildings();
        if (buildings != null && !buildings.isEmpty()) {
            writer.key("buildings").object(buildings);
        }
        writer.endObj();
    }
}
