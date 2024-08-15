package mouse.hoi.main.common.data.scope;

public class StateScope implements Scope{
    private final String origin;
    public StateScope(String origin) {
        this.origin = origin;
    }

    @Override
    public Scope onInteger(int i) {
        String s = String.valueOf(i);
        return new ProvinceScope(s);
    }

    @Override
    public String origin() {
        return origin;
    }

    @Override
    public Scope onTag(String tag) {
        return null;
    }

    @Override
    public ScopeEnum enumValue() {
        return ScopeEnum.STATE;
    }
}
