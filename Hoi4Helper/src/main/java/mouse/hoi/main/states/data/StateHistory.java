package mouse.hoi.main.states.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StateHistory implements Inits {
    private String owner;

    private List<VictoryPoint> victoryPointList;

    private Buildings buildings;
    @Override
    public void initialize() {
        victoryPointList = new ArrayList<>();
    }

    public Buildings buildings() {
        if (buildings == null) {
            buildings = new Buildings();
        }
        return buildings;
    }
}
