package mouse.hoi.main.countries.generator.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CountryColorsWrapper {
    private final List<CountryColors> colorsList;

    public CountryColorsWrapper() {
        colorsList = new ArrayList<>();
    }
}
