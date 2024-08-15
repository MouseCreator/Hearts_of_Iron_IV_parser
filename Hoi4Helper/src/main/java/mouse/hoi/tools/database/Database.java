package mouse.hoi.tools.database;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.database.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Database {
    private final CountryRepository countryRepository;
    public CountryRepository countryRepository() {
        return countryRepository;
    }
}
