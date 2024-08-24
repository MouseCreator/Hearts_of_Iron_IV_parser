package mouse.hoi.main.common.data.trigger.conditional;

import lombok.Data;
import mouse.hoi.main.common.data.scope.Scope;
import mouse.hoi.main.common.data.scope.Scoped;
import mouse.hoi.main.common.data.trigger.scoped.Triggers;
@Data
public class ConditionalTrigger implements Scoped {
    private final Scope scope;
    private Triggers condition;
    private Triggers triggers;

    @Override
    public Scope getScope() {
        return scope;
    }
}
