package mouse.hoi.main.states.service.init;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.properties.FileProperties;
import mouse.hoi.tools.properties.PropertyFiller;
import mouse.hoi.tools.properties.PropertyMap;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateInitService {

    private final PropertyFiller propertyFiller;
    private final FileProperties fileProperties;
    private final StateInitializer stateInitializer;
    public void initAll() {
        PropertyMap map = fileProperties.readProperties("src/main/resources/states/init.input");
        StateInitProperties properties = new StateInitProperties();
        propertyFiller.fillObject(map, properties);
        stateInitializer.initStates(properties.getStateDirectory());
    }
}
