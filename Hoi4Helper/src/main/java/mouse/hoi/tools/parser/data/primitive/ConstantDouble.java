package mouse.hoi.tools.parser.data.primitive;

import mouse.hoi.tools.parser.data.TConstant;

public class ConstantDouble implements TDouble{
    private TConstant tConstant;

    public double value() {
        return tConstant.asDouble();
    }
}
