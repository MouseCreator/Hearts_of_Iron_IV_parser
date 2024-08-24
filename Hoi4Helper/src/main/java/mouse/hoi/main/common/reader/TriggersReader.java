package mouse.hoi.main.common.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.parser.impl.categories.CategoryAssigner;
import mouse.hoi.tools.parser.impl.categories.CategoryMap;
import mouse.hoi.tools.parser.impl.categories.TokenCategory;

import mouse.hoi.main.common.data.scope.Scope;

import mouse.hoi.main.common.data.trigger.Trigger;
import mouse.hoi.main.common.data.trigger.scoped.Triggers;
import mouse.hoi.main.common.data.trigger.conditional.ConditionalTrigger;
import mouse.hoi.main.common.data.trigger.conditional.ConditionalTriggers;
import mouse.hoi.main.common.framework.trigger.TriggerManager;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomKV;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.reader.inits.InitsReader;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TriggersReader implements InitsReader<Triggers> {

    private final CategoryAssigner categoryAssigner;

    private final InterpreterManager interpreterManager;

    private final TriggerManager triggerManager;
    @Override
    public Class<Triggers> forType() {
        return Triggers.class;
    }

    private String key(DomKV domKV) {
        return domKV.key().val().stringValue();
    }

    @Override
    public void read(Triggers triggers, DomData domData) {
        CategoryMap categoryMap = categoryAssigner.mapCategory(domData.object());
        List<DomKV> flowTokens = categoryMap.onCategory(TokenCategory.FLOW);
        for (DomKV domKV : flowTokens) {
            String key = key(domKV);
            switch (key) {
                case "if" -> {
                    ConditionalTrigger ifTrigger = new ConditionalTrigger(triggers.getScope());
                    interpreterManager.fillObject(domKV.value(), ifTrigger);

                    ConditionalTriggers conditionalTriggers = new ConditionalTriggers(triggers.getScope());
                    conditionalTriggers.setIfEffect(ifTrigger);
                    triggers.getConditionalTriggers().add(conditionalTriggers);
                }
                case "else_if" -> {
                    ConditionalTrigger elseeIfTrigger = new ConditionalTrigger(triggers.getScope());
                    interpreterManager.fillObject(domKV.value(), elseeIfTrigger);

                    ConditionalTriggers last = triggers.getConditionalTriggers().getLast();
                    last.addElseIf(elseeIfTrigger);
                }
                case "else" -> {
                    Triggers elseTriggers = new Triggers(triggers.getScope());
                    interpreterManager.fillObject(domKV.value(), elseTriggers);

                    ConditionalTriggers last = triggers.getConditionalTriggers().getLast();
                    last.setElseEffect(elseTriggers);
                }
            }
        }

        List<DomKV> integerTokens = categoryMap.onCategory(TokenCategory.NUMBER);
        for (DomKV domKV : integerTokens) {
            int integer = domKV.key().val().intValue();
            Scope scope = triggers.getScope().onInteger(integer);
            Triggers intTriggers = new Triggers(scope);
            interpreterManager.fillObject(domKV.value(), intTriggers);
            triggers.addSubTriggers(integer, intTriggers);
        }
        List<DomKV> tagTokens = categoryMap.onCategory(TokenCategory.TAG);
        for (DomKV tagToken : tagTokens) {
            String tag = tagToken.key().val().stringValue();
            Scope scope = triggers.getScope().onTag(tag);
            Triggers tagTriggers = new Triggers(scope);
            interpreterManager.fillObject(tagToken.value(), tagTriggers);
            triggers.addSubTriggers(tag, tagTriggers);
        }
        List<DomKV> possibleEffects = categoryMap.onCategory(TokenCategory.EFFECT);
        for (DomKV d : possibleEffects) {
            Trigger trigger = triggerManager.createTrigger(d);
            triggers.addSimpleTrigger(trigger);
        }
    }
}
