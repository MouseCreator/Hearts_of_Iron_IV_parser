package mouse.hoi.main.common.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.common.data.effect.conditional.ConditionalEffect;
import mouse.hoi.main.common.data.effect.conditional.ConditionalEffects;
import mouse.hoi.main.common.data.trigger.Triggers;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConditionalEffectReader implements DataReader<ConditionalEffect> {
    private final Readers readers;
    private final EffectsReader effectsReader;
    @Override
    public Class<ConditionalEffect> forType() {
        return ConditionalEffect.class;
    }

    @Override
    public void onKeyValue(ConditionalEffect effect, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues().with(leftValue, rightValue)
                .onToken("limit").mapBlock(Triggers.class).consume(effect::setTriggers)
                .orElse(()->effectsReader.onKeyValue(effect.getEffects(), leftValue, rightValue));
    }
}
