package mouse.hoi.main.countries.generator.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExtendedColor {
    private String prefix;
    private RGBColor rgbColor;

    public ExtendedColor(String rgb, int r, int g, int b) {
        prefix = rgb;
        rgbColor = new RGBColor(r, g, b);
    }
}
