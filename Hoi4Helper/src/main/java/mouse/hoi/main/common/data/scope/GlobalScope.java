package mouse.hoi.main.common.data.scope;

public class GlobalScope implements Scope {

    private final String origin;

    public GlobalScope(String origin) {
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
        return new CountryScope(origin);
    }

    @Override
    public ScopeEnum enumValue() {
        return ScopeEnum.GLOBAL;
    }
}
