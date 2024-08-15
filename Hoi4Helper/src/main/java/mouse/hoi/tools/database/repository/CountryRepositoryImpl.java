package mouse.hoi.tools.database.repository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class CountryRepositoryImpl implements CountryRepository {

    private final Set<String> countryTags;

    public CountryRepositoryImpl() {
        countryTags = new HashSet<>();
    }

    @Override
    public List<String> allCountryTags() {
        return new ArrayList<>(countryTags);
    }

    @Override
    public boolean hasCountry(String tag) {
        tag = tag.toUpperCase();
        return countryTags.contains(tag);
    }

    @Override
    public void addCountryTag(String tag) {
        tag = tag.toUpperCase();
        countryTags.add(tag);
    }

    @Override
    public void deleteCountryTag(String tag) {
        tag = tag.toUpperCase();
        countryTags.remove(tag);
    }
}
