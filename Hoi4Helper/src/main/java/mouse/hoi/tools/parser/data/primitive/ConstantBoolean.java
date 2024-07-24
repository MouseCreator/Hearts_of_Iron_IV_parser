package mouse.hoi.tools.parser.data.primitive;

import mouse.hoi.tools.parser.data.TConstant;

public class ConstantBoolean implements TBoolean {
    private TConstant value;
    public boolean value() {
        return value.asBoolean();
    }
}
