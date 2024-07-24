package mouse.hoi.tools.parser.data.primitive;

import lombok.Data;

@Data
public class RegularString implements TString {
    private String value;

    public RegularString(String s) {
        this.value = s;
    }
}
