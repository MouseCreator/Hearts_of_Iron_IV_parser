package mouse.hoi.tools.parser.data.primitive;

import lombok.Data;

@Data
public class RegularBoolean implements TBoolean{
    private boolean value;

    public RegularBoolean(boolean v) {
        this.value = v;
    }
}
