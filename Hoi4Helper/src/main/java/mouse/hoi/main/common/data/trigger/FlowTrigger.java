package mouse.hoi.main.common.data.trigger;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mouse.hoi.main.common.data.scope.Scope;
import mouse.hoi.main.common.data.trigger.scoped.Triggers;

@Data
@EqualsAndHashCode(callSuper = false)
public class FlowTrigger extends AbstractTrigger {
    private final Scope scope;
    private final Triggers triggers;

    public FlowTrigger(String key, Scope scope) {
        this.cachedKey = key;
        this.scope = scope;
        triggers = new Triggers(scope);
    }

    @Override
    public String key() {
        return cachedKey;
    }
}
