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
        return database.getCountryRepository().hasCountry(string);
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

    @Override
    public boolean isEffect(String stringKey) {
        return database.getEffectsRepository().isEffect(stringKey);
    }

    @Override
    public boolean isTrigger(String stringKey) {
        return database.getTriggersRepository().isTrigger(stringKey);
    }
}
