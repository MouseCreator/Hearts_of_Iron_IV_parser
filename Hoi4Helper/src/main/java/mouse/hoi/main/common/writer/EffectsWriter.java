package mouse.hoi.main.common.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.exception.EffectException;
import mouse.hoi.main.common.data.effect.EffectMap;
import mouse.hoi.main.common.data.effect.conditional.ConditionalEffects;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.main.common.framework.effect.EffectsWriterHelper;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EffectsWriter implements DataWriter<Effects> {
    private final EffectsWriterHelper effectsWriterHelper;
    private final WriterSupport writerSupport;
    @Override
    public Class<Effects> forType() {
        return Effects.class;
    }

    @Override
    public DWData write(Effects effects, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        List<ConditionalEffects> conditionalEffects = effects.getConditionalEffects();
        conditionalEffects.forEach(e -> effectsWriterHelper.writeConditional(b, e));

        EffectMap effectMap = effects.simpleEffects();
        effectsWriterHelper.writeEffectMap(b, effectMap);

        List<String> subKeys = effects.subKeysSorted();
        for (String key : subKeys) {
            Optional<Effects> subEffect = effects.getSubEffect(key);
            if (subEffect.isEmpty()) {
                throw new EffectException("No subeffect with key " + key);
            }
            Effects subs = subEffect.get();
            b.key(key).objectRaw(subs);
        }
        return b.get();
    }
}
