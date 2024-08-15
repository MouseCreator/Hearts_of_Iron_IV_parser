package mouse.hoi.main.common.data.scope;

public class GlobalScope implements Scope {
    @Override
    public Scope onInteger(int i) {
        String origin = String.valueOf(i);
        return new StateScope(origin);
    }

    @Override
    public String origin() {
        return null;
    }

    @Override
    public Scope onTag(String tag) {
        return null;
    }

    @Override
    public ScopeEnum enumValue() {
        return ScopeEnum.GLOBAL;
    }
}
