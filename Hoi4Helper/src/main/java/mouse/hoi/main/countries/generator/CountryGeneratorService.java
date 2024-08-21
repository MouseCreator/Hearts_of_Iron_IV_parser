package mouse.hoi.main.countries.generator;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.context.AppService;
import mouse.hoi.tools.properties.FileProperties;
import mouse.hoi.tools.properties.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryGeneratorService implements AppService {

    private final CountriesGenerator countriesGenerator;
    private final FileProperties fileProperties;
    private final CountryInputLoader loader;
    @Override
    public void start() {
        PropertyMap map = fileProperties.readProperties("src/main/resources/base/init.input");
        String modDirectory = map.expectedProperty("directory");
        List<CountryInput> input = loader.createInput();
        countriesGenerator.generateCountries(modDirectory, input);
    }
}
