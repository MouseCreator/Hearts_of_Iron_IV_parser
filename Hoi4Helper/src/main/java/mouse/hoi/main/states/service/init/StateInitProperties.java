package mouse.hoi.main.states.service.init;

import lombok.Data;
import mouse.hoi.tools.properties.annotation.FromProperty;

@Data
public class StateInitProperties {
    @FromProperty("directory")
    private String stateDirectory;
}
