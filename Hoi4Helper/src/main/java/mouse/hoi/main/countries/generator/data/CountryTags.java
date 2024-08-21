package mouse.hoi.main.countries.generator.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CountryTags {
    private List<CountryTag> countryTagList;

    public CountryTags() {
        countryTagList = new ArrayList<>();
    }
}
