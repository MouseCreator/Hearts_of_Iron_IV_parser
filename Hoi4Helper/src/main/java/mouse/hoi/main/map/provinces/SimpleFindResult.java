package mouse.hoi.main.map.provinces;

import lombok.Data;
import mouse.hoi.main.map.Pos;

@Data
public class SimpleFindResult implements ProvinceFindResult {
    private Pos pos;
    private ProvinceInfo provinceInfo;
    public static SimpleFindResult of(ProvinceInfo info, Pos middle) {
        SimpleFindResult result = new SimpleFindResult();
        result.provinceInfo = info;
        result.pos = middle;
        return result;
    }

    @Override
    public String print() {
        return provinceInfo + "\nSample position: " + pos;
    }
}
