package mouse.hoi.main.common.data.scope;

import mouse.hoi.exception.ScopeException;

public class ProvinceScope implements Scope {
    public ProvinceScope() {
    }

    @Override
    public Scope onInteger(int i) {
        throw new ScopeException("Province scope: got unexpected integer: " + i);
    }

    @Override
    public Scope onTag(String tag) {
        return new CountryScope();
    }

    @Override
    public ScopeEnum enumValue() {
        return ScopeEnum.PROVINCE;
    }
}
