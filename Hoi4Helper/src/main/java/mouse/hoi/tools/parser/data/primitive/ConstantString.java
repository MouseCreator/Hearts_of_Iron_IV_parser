package mouse.hoi.tools.parser.data.primitive;

import mouse.hoi.tools.parser.data.TConstant;

public class ConstantString implements TString {
    private TConstant tConstant;

    public String value() {
        return tConstant.asString();
    }
}
