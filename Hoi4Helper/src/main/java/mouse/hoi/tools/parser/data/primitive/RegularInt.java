package mouse.hoi.tools.parser.data.primitive;

import lombok.Data;

@Data
public class RegularInt implements TInt{
    private int value;

    public RegularInt(int i) {
        this.value = i;
    }
}
