package mouse.hoi.main.map.provinces;

import mouse.hoi.exception.ProvinceException;

import java.util.Map;
import java.util.Optional;

public class ProvinceDefinitions {
    private final Map<ProvinceColor, ProvinceInfo> byColor;
    private final Map<Integer, ProvinceInfo> byId;
    public ProvinceDefinitions(Map<ProvinceColor, ProvinceInfo> colorMap,
                              Map<Integer, ProvinceInfo> byId) {
        this.byColor = colorMap;
        this.byId = byId;
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
}
