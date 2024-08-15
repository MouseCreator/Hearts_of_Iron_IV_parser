package mouse.hoi.main.common.tester;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.database.Database;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenTesterImpl implements TokenTester {
    private final Database database;
    @Override
    public boolean isCountryTag(String string) {
        return database.countryRepository().hasCountry(string);
    }

    @Override
    public boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
