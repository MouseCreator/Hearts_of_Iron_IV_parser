package mouse.hoi.main.common.data.scope;


public interface Scope {
    Scope onInteger(int i);
    Scope onTag(String tag);
    ScopeEnum enumValue();
}
