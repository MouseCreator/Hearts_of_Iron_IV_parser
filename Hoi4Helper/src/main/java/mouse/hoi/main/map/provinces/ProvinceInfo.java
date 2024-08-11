package mouse.hoi.main.map.provinces;

import lombok.Data;

@Data
public class ProvinceInfo {

    private int id;
    private int red;
    private int green;
    private int blue;
    private String type;
    private boolean coastal;
    private String terrain;
    private int continent;

    public ProvinceColor color() {
        return new ProvinceColor(red, green, blue);
    }
}
