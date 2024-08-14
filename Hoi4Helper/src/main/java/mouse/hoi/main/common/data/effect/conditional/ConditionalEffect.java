package mouse.hoi.main.common.data.effect.conditional;

import lombok.Data;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.main.common.data.scope.Scope;
import mouse.hoi.main.common.data.scope.Scoped;
import mouse.hoi.main.common.data.trigger.Triggers;
@Data
public class ConditionalEffect implements Scoped {
    private Scope scope;
    private Triggers triggers;
    private Effects effects;

    public ConditionalEffect(Scope scope) {
        triggers = new Triggers(scope);
        effects = new Effects(scope);
    }
}
