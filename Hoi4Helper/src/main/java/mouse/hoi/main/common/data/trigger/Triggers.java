package mouse.hoi.main.common.data.trigger;

import mouse.hoi.main.common.data.scope.Scope;
import mouse.hoi.main.common.data.scope.Scoped;

public class Triggers implements Scoped {
    private final Scope scope;
    public Triggers(Scope scope) {
        this.scope = scope;
    }

    @Override
    public Scope getScope() {
        return scope;
    }
}
