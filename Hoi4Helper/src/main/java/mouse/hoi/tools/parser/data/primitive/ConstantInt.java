package mouse.hoi.tools.parser.data.primitive;

import mouse.hoi.tools.parser.data.TConstant;

public class ConstantInt implements TInt{
    private TConstant constant;

    public int value() {
        return constant.asInt();
    }
}
