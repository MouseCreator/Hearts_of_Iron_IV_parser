package mouse.hoi.main.units;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
public class UnitsInput {
    @Setter
    private String globalSuffix;
    private final List<CountryUnitsDescription> list;

    public UnitsInput() {
        list = new ArrayList<>();
    }
}
