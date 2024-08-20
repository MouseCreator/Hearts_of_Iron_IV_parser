package mouse.hoi.main.common.data.scope;

public class CountryScope implements Scope{


    public CountryScope() {

    }

    @Override
    public Scope onInteger(int i) {
        return new StateScope();
    }

    @Override
    public Scope onTag(String tag) {
        return new StateScope();
    }

    @Override
    public ScopeEnum enumValue() {
        return ScopeEnum.COUNTRY;
    }
}
