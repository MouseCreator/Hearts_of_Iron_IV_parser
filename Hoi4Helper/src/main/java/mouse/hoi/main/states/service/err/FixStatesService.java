package mouse.hoi.main.states.service.err;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.context.AppService;
import mouse.hoi.tools.properties.FileProperties;
import mouse.hoi.tools.properties.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class FixStatesService implements AppService {

    private final StatesOnError fix;
    private final FileProperties fileProperties;
    @Override
    public void start() {
        PropertyMap map = fileProperties.readProperties("src/main/resources/states/init.input");
        String directory = map.expectedProperty("directory");
        Function<String, String> func = s -> s.replace("GHA", "NOO");
        fix.fixErrors(directory, func);
    }
}
