package mouse.hoi.tools.database.repository;

import java.util.List;

public interface CountryRepository {
    List<String> allCountryTags();
    boolean hasCountry(String tag);
    void addCountryTag(String tag);
    void deleteCountryTag(String tag);
}
