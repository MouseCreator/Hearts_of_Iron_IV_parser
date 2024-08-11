package mouse.hoi.main.map.provinces;

import mouse.hoi.exception.ProvinceException;
import mouse.hoi.main.map.Pos;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProvinceCollection {
    private final Map<Pos, ProvinceInfo> byPos;
    private final Map<ProvinceColor, ProvinceInfo> byColor;
    private final Map<Integer, Pos> allProvincePositions;
    private final Map<Integer, ProvinceInfo> byId;
    public ProvinceCollection(Map<ProvinceColor, ProvinceInfo> colorMap,
                              Map<Pos, ProvinceInfo> posMap,
                              Map<Integer, Pos> allProvincePositions,
                              Map<Integer, ProvinceInfo> byId) {
        this.byPos = posMap;
        this.byColor = colorMap;
        this.allProvincePositions = allProvincePositions;
        this.byId = byId;
    }
    public Optional<ProvinceInfo> getProvinceByPosition(int x, int y) {
        Pos pos = Pos.of(x, y);
        return Optional.ofNullable(byPos.get(pos));
    }
    public Optional<ProvinceInfo> getProvinceByColor(ProvinceColor color) {
        return Optional.ofNullable(byColor.get(color));
    }
    public ProvinceInfo getProvinceById(int provinceId) {
        ProvinceInfo provinceInfo = byId.get(provinceId);
        if (provinceInfo == null) {
            throw new ProvinceException("No province with id: " + provinceId);
        }
        return provinceInfo;
    }
    public Pos provincePosition(int provinceId) {
        Pos pos = allProvincePositions.get(provinceId);
        if (pos == null) {
            throw new ProvinceException("No province with id: " + provinceId);
        }
        return pos;
    }

    public Pos provincePosition(ProvinceInfo prov) {
        return provincePosition(prov.getId());
    }
}
