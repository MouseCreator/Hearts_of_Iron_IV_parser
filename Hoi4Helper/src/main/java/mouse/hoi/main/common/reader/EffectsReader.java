package mouse.hoi.main.common.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.parser.impl.categories.CategoryAssigner;
import mouse.hoi.tools.parser.impl.categories.CategoryMap;
import mouse.hoi.tools.parser.impl.categories.TokenCategory;
import mouse.hoi.main.common.data.effect.Effect;
import mouse.hoi.main.common.data.effect.conditional.ConditionalEffect;
import mouse.hoi.main.common.data.effect.conditional.ConditionalEffects;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.main.common.data.scope.Scope;
import mouse.hoi.main.common.framework.effect.EffectManager;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomKV;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.reader.inits.InitsReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EffectsReader implements InitsReader<Effects> {
    private final EffectManager effectManager;
    private final InterpreterManager interpreterManager;
    private final CategoryAssigner categoryAssigner;
    @Override
    public Class<Effects> forType() {
        return Effects.class;
    }
    @Override
    public void read(Effects effects, DomData domData) {
        CategoryMap categoryMap = categoryAssigner.mapCategory(domData.object());
        List<DomKV> flowTokens = categoryMap.onCategory(TokenCategory.FLOW);
        for (DomKV domKV : flowTokens) {
            String key = key(domKV);
            switch (key) {
                case "if" -> {
                    ConditionalEffect ifEffect = new ConditionalEffect(effects.getScope());
                    interpreterManager.fillObject(domKV.value(), ifEffect);

                    ConditionalEffects conditionalEffects = new ConditionalEffects(effects.getScope());
                    conditionalEffects.setIfEffect(ifEffect);
                    effects.getConditionalEffects().add(conditionalEffects);
                }
                case "else_if" -> {
                    ConditionalEffect elseIfEffect = new ConditionalEffect(effects.getScope());
                    interpreterManager.fillObject(domKV.value(), elseIfEffect);

                    ConditionalEffects last = effects.getConditionalEffects().getLast();
                    last.addElseIf(elseIfEffect);
                }
                case "else" -> {
                    Effects elseEffects = new Effects(effects.getScope());
                    interpreterManager.fillObject(domKV.value(), elseEffects);

                    ConditionalEffects last = effects.getConditionalEffects().getLast();
                    last.setElseEffect(elseEffects);
                }
            }
        }
        List<DomKV> integerTokens = categoryMap.onCategory(TokenCategory.NUMBER);
        for (DomKV domKV : integerTokens) {
            int integer = domKV.key().val().intValue();
            Scope scope = effects.getScope().onInteger(integer);
            Effects intEffects = new Effects(scope);
            interpreterManager.fillObject(domKV.value(), intEffects);
            effects.addSubEffects(integer, intEffects);
        }
        List<DomKV> tagTokens = categoryMap.onCategory(TokenCategory.TAG);
        for (DomKV tagKV : tagTokens) {
            String string = tagKV.key().val().stringValue();
            Scope scope = effects.getScope().onTag(string);
            Effects tagEffects = new Effects(scope);
            interpreterManager.fillObject(tagKV.value(), tagEffects);
            effects.addSubEffects(string, tagEffects);
        }
        List<DomKV> possibleEffects = categoryMap.onCategory(TokenCategory.EFFECT);
        for (DomKV d : possibleEffects) {
            Effect effect = effectManager.createEffect(d, effects.getScope());
            effects.simpleEffects().putEffect(effect);
        }
    }

    private String key(DomKV domKV) {
        return domKV.key().val().stringValue();
    }
}
