package mouse.hoi.main.common.data.scope;

public class GlobalScope implements Scope {


    public GlobalScope() {

    }

    @Override
    public Scope onInteger(int i) {
        return new StateScope();
    }


    @Override
    public Scope onTag(String tag) {
        return new CountryScope();
    }

    @Override
    public ScopeEnum enumValue() {
        return ScopeEnum.GLOBAL;
    }
}
