package mouse.hoi.main.map.provinces;

import lombok.Data;

import java.awt.*;

@Data
public class ProvinceColor {
    private int red;
    private int greed;
    private int blue;

    public ProvinceColor(int red, int green, int blue) {
        this.red = red;
        this.greed = green;
        this.blue = blue;
    }

    public ProvinceColor(Color color) {
        this.red = color.getRed();
        this.blue = color.getBlue();
        this.greed = color.getGreen();
    }

    public Color color() {
        return new Color(red, greed, blue);
    }
}
