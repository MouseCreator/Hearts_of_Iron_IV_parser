package mouse.hoi.main.states.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProvinceBuilding {
    private int province;
    private List<Building> buildingList;

    public ProvinceBuilding() {
        this.buildingList = new ArrayList<>();
    }
}
