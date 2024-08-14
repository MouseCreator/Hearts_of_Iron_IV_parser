package mouse.hoi.main.common.data.effect.conditional;

import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.main.common.data.scope.Scope;
import mouse.hoi.main.common.data.scope.Scoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConditionalEffects implements Scoped {
    private Scope scope;
    private ConditionalEffect if_Effect;
    private final List<ConditionalEffect> elseIf_Effect;
    private Effects else_Effect;
    public ConditionalEffects(Scope scope) {
        this.scope = scope;
        if_Effect = null;
        elseIf_Effect = new ArrayList<>();
        else_Effect = null;
    }

    public void setIfEffect(ConditionalEffect conditionalEffect) {
        this.if_Effect = conditionalEffect;
    }
    public void addElseIf(ConditionalEffect conditionalEffect) {
        elseIf_Effect.add(conditionalEffect);
    }
    public void setElseEffect(Effects conditionalEffect) {
        else_Effect = conditionalEffect;
    }

    public Optional<Effects> elseOptional() {
        return Optional.ofNullable(else_Effect);
    }

    @Override
    public Scope getScope() {
        return scope;
    }
}
