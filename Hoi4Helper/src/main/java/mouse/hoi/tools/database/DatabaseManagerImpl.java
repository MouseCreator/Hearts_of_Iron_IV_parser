package mouse.hoi.tools.database;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.countries.CountryTagLoader;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DatabaseManagerImpl implements DatabaseManager {
    private final Database database;
    private final CountryTagLoader countryTagLoader;
    @Override
    public void loadCountries() {
        Collection<String> allCountries = countryTagLoader.getAllCountries();
        for (String tag : allCountries) {
            database.countryRepository().addCountryTag(tag);
        }
    }
}
