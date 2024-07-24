package mouse.hoi.tools.parser.data.primitive;

import lombok.Data;

@Data
public class RegularDouble implements TDouble{
    private double value;

    public RegularDouble(double d) {
        this.value = d;
    }
}
