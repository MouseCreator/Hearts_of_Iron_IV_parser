package mouse.hoi.main.units;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CountryUnitsDescription {
    private String tag;
    private List<TemplateDescription> templates;

    public CountryUnitsDescription() {
        templates = new ArrayList<>();
    }
}
