package mouse.hoi.tools.parser.data.primitive;

import mouse.hoi.tools.parser.data.TScope;

public class ReferencedValue implements TBoolean, TDouble, TInt, TString {
    private TScope fromScope;
    private String variableName;
}
