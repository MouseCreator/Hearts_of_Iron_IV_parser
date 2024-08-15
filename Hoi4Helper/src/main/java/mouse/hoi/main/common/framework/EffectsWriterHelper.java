package mouse.hoi.main.common.framework;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.common.data.effect.Effect;
import mouse.hoi.main.common.data.effect.EffectMap;
import mouse.hoi.main.common.data.effect.conditional.ConditionalEffect;
import mouse.hoi.main.common.data.effect.conditional.ConditionalEffects;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EffectsWriterHelper {
    private final EffectManager effectManager;
    public void writeConditional(SpecialWriter specialWriter, ConditionalEffects conditionalEffects) {
        ConditionalEffect ifEffect = conditionalEffects.getIf_Effect();
        specialWriter.write("if").eq().beginObj();
        writeConditionalEffectInner(ifEffect, specialWriter);
        specialWriter.endObj();
        List<ConditionalEffect> elseIfEffects = conditionalEffects.getElseIf_Effects();
        for (ConditionalEffect elseIf : elseIfEffects) {
            specialWriter.write("else_if").eqb();
            writeConditionalEffectInner(elseIf, specialWriter);
            specialWriter.endObj();
        }
        Optional<Effects> optionalElse = conditionalEffects.elseOptional();
        if (optionalElse.isEmpty()) {
            return;
        }
        Effects elseEffects = optionalElse.get();
        specialWriter.write("else").eqb().writeObject(elseEffects).endObj();
    }

    private void writeConditionalEffectInner(ConditionalEffect conditionalEffect, SpecialWriter specialWriter) {
        specialWriter.key("limit").object(conditionalEffect::getTriggers);
        specialWriter.writeObject(conditionalEffect.getEffects());
    }

    public void writeEffectMap(SpecialWriter writer, EffectMap effectMap) {
        List<Effect> effects = effectMap.allEffects();
        for (Effect effect : effects) {
            effectManager.writeEffect(writer, effect);
        }
    }
}
