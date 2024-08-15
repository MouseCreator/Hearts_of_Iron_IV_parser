package mouse.hoi.main.common.data.trigger.conditional;

import mouse.hoi.main.common.data.scope.Scope;
import mouse.hoi.main.common.data.scope.Scoped;
import mouse.hoi.main.common.data.trigger.Triggers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConditionalTriggers implements Scoped {
    private final Scope scope;
    private ConditionalTrigger if_Trigger;
    private final List<ConditionalTrigger> elseIf_Triggers;
    private Triggers else_Trigger;
    public ConditionalTriggers(Scope scope) {
        this.scope = scope;
        if_Trigger = null;
        elseIf_Triggers = new ArrayList<>();
        else_Trigger = null;
    }

    public void setIfEffect(ConditionalTrigger conditionalEffect) {
        this.if_Trigger = conditionalEffect;
    }
    public void addElseIf(ConditionalTrigger conditionalEffect) {
        elseIf_Triggers.add(conditionalEffect);
    }
    public void setElseEffect(Triggers conditionalEffect) {
        else_Trigger = conditionalEffect;
    }

    public Optional<Triggers> elseOptional() {
        return Optional.ofNullable(else_Trigger);
    }

    @Override
    public Scope getScope() {
        return scope;
    }
}
