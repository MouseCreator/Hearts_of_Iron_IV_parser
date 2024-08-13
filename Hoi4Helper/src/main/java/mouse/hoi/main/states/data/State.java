package mouse.hoi.main.states.data;

import lombok.Data;

import java.util.List;

@Data
public class State {
    private int id;
    private String name;
    private int manpower;
    private String category;
    private List<Integer> provinces;
    private double localSupplies;
    private double buildingsMaxLevelFactor;
    private StateHistory stateHistory;

    public StateHistory historyOrInit() {
        if (stateHistory == null){
            stateHistory = new StateHistory();
        }
        return stateHistory;
    }
}
