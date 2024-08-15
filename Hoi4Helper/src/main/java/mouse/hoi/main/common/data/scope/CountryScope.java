package mouse.hoi.main.common.data.scope;

public class CountryScope implements Scope{
    private final String origin;

    public CountryScope(String origin) {
        this.origin = origin;
    }

    @Override
    public Scope onInteger(int i) {
        String origin = String.valueOf(i);
        return new StateScope(origin);
    }

    @Override
    public String origin() {
        return origin;
    }

    @Override
    public Scope onTag(String tag) {
        return new StateScope(tag);
    }

    @Override
    public ScopeEnum enumValue() {
        return ScopeEnum.COUNTRY;
    }
}
