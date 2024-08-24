package mouse.hoi.main.common.data.trigger.scoped;

import lombok.Getter;
import mouse.hoi.main.common.data.scope.Scope;
import mouse.hoi.main.common.data.scope.Scoped;
import mouse.hoi.main.common.data.trigger.FlowTrigger;
import mouse.hoi.main.common.data.trigger.Trigger;
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
    private final List<Trigger> simpleTriggers;
    public Triggers(Scope scope) {
        this.scope = scope;
        conditionalTriggers = new ArrayList<>();
        flowTriggers = new ArrayList<>();
        simpleTriggers = new ArrayList<>();
        subTriggers = new HashMap<>();
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    public void addSubTriggers(String key, Triggers triggers) {
        subTriggers.put(key, triggers);
    }

    public void addSubTriggers(int key, Triggers triggers) {
        subTriggers.put(String.valueOf(key), triggers);
    }

    public void addSimpleTrigger(Trigger trigger) {
        simpleTriggers.add(trigger);
    }
}
