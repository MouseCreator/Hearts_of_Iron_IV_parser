package mouse.hoi.main.states.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StateHistory {
    private String owner;

    private List<VictoryPoint> victoryPointList;

    private Buildings buildings;

    private List<String> cores;

    private List<String> claims;

    public StateHistory() {
        victoryPointList = new ArrayList<>();
        cores = new ArrayList<>();
    }

    public Buildings buildings() {
        if (buildings == null) {
            buildings = new Buildings();
        }
        return buildings;
    }
}
