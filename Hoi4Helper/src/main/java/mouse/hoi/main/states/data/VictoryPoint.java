package mouse.hoi.main.states.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VictoryPoint {
    private int province;
    private int points;
    public VictoryPoint(int province, int points) {
        this.province = province;
        this.points = points;
    }
}
