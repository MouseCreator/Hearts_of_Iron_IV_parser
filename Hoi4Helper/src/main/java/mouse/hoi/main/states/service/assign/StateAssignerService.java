package mouse.hoi.main.states.service.assign;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.properties.FileProperties;
import mouse.hoi.tools.properties.PropertyMap;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class StateAssignerService {


    private final FileProperties fileProperties;
    private final StateAssignerMult stateAssignerMult;
    public void assign() {
        PropertyMap map = fileProperties.readProperties("src/main/resources/states/assign.input");
        String dir = map.expectedProperty("directory");
        String tag = map.expectedProperty("tag");
        stateAssignerMult.assignAllTo(dir, tag);
    }
}
