package mouse.hoi.main.countries;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.files.FileManager;
import mouse.hoi.tools.properties.FileProperties;
import mouse.hoi.tools.properties.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryTagLoader {
    private final FileManager fileManager;
    private final FileProperties fileProperties;
    public Collection<String> getAllCountries() {
        PropertyMap map = fileProperties.readProperties("src/main/resources/countries/init.input");
        String tagsFile = map.expectedProperty("tags");
        List<String> countryTags = fileManager.readLines(tagsFile);
        List<String> tags = new ArrayList<>();
        for (String line : countryTags) {
            line = line.trim();
            if (line.isEmpty())
                continue;
            String[] split = line.split("=", 2);
            String tag = split[0].trim();
            tags.add(tag);
        }
        return tags;
    }
}
