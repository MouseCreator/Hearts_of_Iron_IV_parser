package mouse.hoi.main.countries.generator;

import lombok.Data;

@Data
public class CountryInput {
    private String extraContent;
    private String tag;
    private int capital;
    private int r;
    private int b;
    private int g;
    private String fullName;
}
