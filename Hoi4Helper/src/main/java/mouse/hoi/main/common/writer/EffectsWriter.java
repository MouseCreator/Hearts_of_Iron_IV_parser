package mouse.hoi.main.common.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.exception.EffectException;
import mouse.hoi.main.common.data.effect.EffectMap;
import mouse.hoi.main.common.data.effect.conditional.ConditionalEffects;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.main.common.framework.EffectsWriterHelper;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EffectsWriter implements DataWriter<Effects> {
    private final EffectsWriterHelper effectsWriterHelper;
    @Override
    public Class<Effects> forType() {
        return Effects.class;
    }

    @Override
    public void write(SpecialWriter writer, Effects effects) {
        List<ConditionalEffects> conditionalEffects = effects.getConditionalEffects();
        for (ConditionalEffects effectsCd : conditionalEffects) {
            effectsWriterHelper.writeConditional(writer, effectsCd);
        }
        EffectMap effectMap = effects.simpleEffects();
        effectsWriterHelper.writeEffectMap(writer, effectMap);
        Set<String> strings = effects.subKeys();
        for (String key : strings) {
            Optional<Effects> subEffect = effects.getSubEffect(key);
            if (subEffect.isEmpty()) {
                throw new EffectException("No subeffect with key " + key);
            }
            Effects subs = subEffect.get();
            writer.write(key).eqb().writeObject(subs).endObj();
        }
    }
}
