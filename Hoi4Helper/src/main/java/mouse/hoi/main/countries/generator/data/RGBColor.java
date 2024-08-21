package mouse.hoi.main.countries.generator.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RGBColor {
    private int r;
    private int g;
    private int b;

    public RGBColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
