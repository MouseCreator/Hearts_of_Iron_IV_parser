package mouse.hoi.main.map.provinces;

import lombok.Data;
import mouse.hoi.tools.properties.annotation.FromProperty;

@Data
public class ProvinceProperties {
    @FromProperty("definition")
    private String definitionsFile;
    @FromProperty("provinces")
    private String provinceMap;
    @FromProperty("find")
    private String toFind;
}
