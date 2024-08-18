package mouse.hoi.main.bookmark.data;

import lombok.Data;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.tools.parser.data.GameDate;

import java.util.ArrayList;
import java.util.List;

@Data
public class Bookmark {
    private String name;
    private String desc;
    private GameDate date;
    private String picture;
    private boolean isDefault;
    private String defaultCountry;
    private Effects effects;
    private List<CountryDescription> countryDescriptionList;

    public Bookmark() {
        countryDescriptionList = new ArrayList<>();
    }
}
