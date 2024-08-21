package mouse.hoi.main.countries.generator.data;

import lombok.Data;

@Data
public class CountryTag {
    private String tag;
    private String location;

    public CountryTag(String tag, String value) {
        this.tag = tag;
        this.location = value;
    }
}
