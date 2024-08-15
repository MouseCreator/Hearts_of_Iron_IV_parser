package mouse.hoi.main.common.data.trigger;

import lombok.Getter;
import mouse.hoi.main.common.data.scope.Scope;
import mouse.hoi.main.common.data.scope.Scoped;
import mouse.hoi.main.common.data.trigger.conditional.ConditionalTriggers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Triggers implements Scoped {
    private final Scope scope;
    @Getter
    private final List<ConditionalTriggers> conditionalTriggers;
    @Getter
    private final List<FlowTrigger> flowTriggers;
    private final Map<String, Triggers> subTriggers;
    public Triggers(Scope scope) {
        this.scope = scope;
        conditionalTriggers = new ArrayList<>();
        flowTriggers = new ArrayList<>();
        subTriggers = new HashMap<>();
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    public void addSubTriggers(Triggers triggers) {
        String origin = triggers.getScope().origin();
        subTriggers.put(origin, triggers);
    }
}
