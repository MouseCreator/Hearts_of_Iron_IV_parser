package mouse.hoi.main.common.data.trigger;

import mouse.hoi.main.common.data.scope.Scope;
import mouse.hoi.main.common.data.scope.Scoped;

public class FlowTrigger implements Scoped {
    private final String key;
    private final Scope scope;
    private final Triggers triggers;

    public FlowTrigger(String key, Scope scope) {
        this.key = key;
        this.scope = scope;
        triggers = new Triggers(scope);
    }

    @Override
    public Scope getScope() {
        return scope;
    }
}
