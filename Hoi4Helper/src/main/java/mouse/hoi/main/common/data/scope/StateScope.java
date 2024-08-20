package mouse.hoi.main.common.data.scope;

public class StateScope implements Scope{

    public StateScope() {}

    @Override
    public Scope onInteger(int i) {
        return new ProvinceScope();
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
