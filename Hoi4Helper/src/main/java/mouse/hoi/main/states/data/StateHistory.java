package mouse.hoi.main.states.data;

import lombok.Data;
import mouse.hoi.tools.parser.impl.reader.Inits;

import java.util.ArrayList;
import java.util.List;

@Data
public class StateHistory implements Inits {
    private String owner;

    private List<VictoryPoint> victoryPointList;
    @Override
    public void initialize() {
        victoryPointList = new ArrayList<>();
    }

}
