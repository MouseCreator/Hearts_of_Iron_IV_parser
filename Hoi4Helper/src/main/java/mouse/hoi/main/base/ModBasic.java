package mouse.hoi.main.base;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.properties.FileProperties;
import mouse.hoi.tools.properties.PropertyMap;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModBasic {
    private final FileProperties fileProperties;
    public String modDirectory() {
        PropertyMap map = fileProperties.readProperties("src/main/resources/base/init.input");
        return map.expectedProperty("directory");
    }
}
