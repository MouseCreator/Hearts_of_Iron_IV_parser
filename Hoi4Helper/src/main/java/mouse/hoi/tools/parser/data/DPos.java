package mouse.hoi.tools.parser.data;

import lombok.Data;
import mouse.hoi.tools.parser.annotation.Accuracy;
import mouse.hoi.tools.parser.annotation.GameObj;
import mouse.hoi.tools.parser.annotation.WriteAs;
import mouse.hoi.tools.parser.data.primitive.TDouble;

@Data
@GameObj
public class DPos {
    @Accuracy(3)
    @WriteAs
    private double x;
    @Accuracy(3)
    @WriteAs
    private double y;
}
