package mouse.hoi.main.common.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.common.data.effect.conditional.ConditionalEffect;
import mouse.hoi.main.common.data.effect.conditional.ConditionalEffects;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.tools.database.TokenTester;
import mouse.hoi.tools.parser.impl.ast.BlockNode;
import mouse.hoi.tools.parser.impl.ast.Node;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class EffectsReader implements DataReader<Effects> {
    private final Readers readers;
    private final TokenTester tester;
    @Override
    public Class<Effects> forType() {
        return Effects.class;
    }

    @Override
    public void onKeyValue(Effects effects, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues().with(leftValue, rightValue)
                .onToken("if")
                    .init(()->new ConditionalEffect(effects.getScope()))
                    .onBlock().res().consume(c -> {
                        ConditionalEffects conditionalEffects = new ConditionalEffects(effects.getScope());
                        conditionalEffects.setIfEffect(c);
                        effects.getConditionalEffects().add(conditionalEffects);
                })
                .onToken("else_if")
                    .init(()->new ConditionalEffect(effects.getScope()))
                    .onBlock().res().consume(c -> {
                        ConditionalEffects last = effects.getConditionalEffects().getLast();
                        last.addElseIf(c);
                })
                .onToken("else")
                    .init(()->new Effects(effects.getScope()))
                    .onBlock().res().consume(c -> {
                        ConditionalEffects last = effects.getConditionalEffects().getLast();
                        last.setElseEffect(c);
                })
                .rememberInteger()
                    .map(i -> effects.getScope().onInteger()).map(Effects::new)
                    .onBlock().res().consume(effects::addSubEffects)
                .rememberString()
                    .test(tester::isCountryTag).map(s -> effects.getScope().onTag()).map(Effects::new)
                    .onBlock().res().consume(effects::addSubEffects)
                .orElseThrow();

    }


}
