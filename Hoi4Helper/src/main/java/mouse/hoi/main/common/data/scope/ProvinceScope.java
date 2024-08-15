package mouse.hoi.main.common.data.scope;

import mouse.hoi.exception.ScopeException;

public class ProvinceScope implements Scope {
    private final String origin;
    public ProvinceScope(String origin) {
        this.origin = origin;
    }

    @Override
    public Scope onInteger(int i) {
        throw new ScopeException("Province scope: got unexpected integer: " + i);
    }

    @Override
    public String origin() {
        return origin;
    }

    @Override
    public Scope onTag(String tag) {
        return new CountryScope(tag);
    }

    @Override
    public ScopeEnum enumValue() {
        return ScopeEnum.PROVINCE;
    }
}
