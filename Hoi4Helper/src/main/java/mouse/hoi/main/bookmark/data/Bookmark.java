package mouse.hoi.main.bookmark.data;

import lombok.Data;
import mouse.hoi.main.common.data.effect.scoped.Effects;

import java.util.ArrayList;
import java.util.List;

@Data
public class Bookmark implements Inits {
    private String name;
    private String desc;
    private String date;
    private String picture;
    private boolean isDefault;
    private String defaultCountry;
    private Effects effects;
    private List<CountryDescription> countryDescriptionList;

    @Override
    public void initialize() {
        this.countryDescriptionList = new ArrayList<>();
    }
}
