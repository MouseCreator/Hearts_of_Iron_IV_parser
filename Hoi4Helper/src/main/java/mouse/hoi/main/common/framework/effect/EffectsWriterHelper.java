package mouse.hoi.main.common.framework.effect;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.common.data.effect.Effect;
import mouse.hoi.main.common.data.effect.EffectMap;
import mouse.hoi.main.common.data.effect.conditional.ConditionalEffect;
import mouse.hoi.main.common.data.effect.conditional.ConditionalEffects;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EffectsWriterHelper {
    private final EffectManager effectManager;
    private final WriterSupport writerSupport;
    public void writeEffectMap(DWObjectBuilder b, EffectMap effectMap) {
        List<Effect> effects = effectMap.allEffects();
        for (Effect effect : effects) {
            effectManager.writeEffect(b, effect);
        }
    }

    public void writeConditional(DWObjectBuilder b, ConditionalEffects conditionalEffects) {
        ConditionalEffect ifEffect = conditionalEffects.getIf_Effect();
        DWData ifData = writeConditionalInner(ifEffect);
        b.key("if").value(ifData);
        List<ConditionalEffect> elseIfEffects = conditionalEffects.getElseIf_Effects();
        for (ConditionalEffect elseIf : elseIfEffects) {
            DWData dwData = writeConditionalInner(elseIf);
            b.key("else_if").value(dwData);
        }
        Optional<Effects> optionalElse = conditionalEffects.elseOptional();
        if (optionalElse.isEmpty()) {
            return;
        }
        Effects elseEffects = optionalElse.get();
        b.key("else").objectRaw(elseEffects);
    }

    private DWData writeConditionalInner(ConditionalEffect conditionalEffect) {
        DWObjectBuilder s = writerSupport.build();
        s.key("limit").object(conditionalEffect::getTriggers);
        s.embedded(conditionalEffect::getEffects, ObjectStyle.DEFAULT);
        return s.get();
    }
}
