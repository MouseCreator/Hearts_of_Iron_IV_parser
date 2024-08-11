package mouse.hoi.tools.parser.data;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DPos {
    private double x;
    private double y;

    public DPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static DPos of(int x, int y) {
        return new DPos(x ,y);
    }
}
