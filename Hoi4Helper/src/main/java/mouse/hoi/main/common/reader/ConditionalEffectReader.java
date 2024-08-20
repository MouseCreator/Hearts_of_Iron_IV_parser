package mouse.hoi.main.common.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.common.data.effect.conditional.ConditionalEffect;
import mouse.hoi.main.common.data.effect.scoped.Effects;
import mouse.hoi.main.common.data.trigger.Triggers;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomObject;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.inits.InitsReader;
import mouse.hoi.tools.parser.impl.split.CategorySplitDescription;
import mouse.hoi.tools.parser.impl.split.CategorySplitManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConditionalEffectReader implements InitsReader<ConditionalEffect> {
    private final DomQueryService queryService;
    private final InterpreterManager interpreterManager;
    private final CategorySplitManager categorySplitManager;
    @Override
    public Class<ConditionalEffect> forType() {
        return ConditionalEffect.class;
    }

    @Override
    public void read(ConditionalEffect conditionalEffect, DomData domData) {
        List<CategorySplitDescription> categories = categories();
        List<DomObject> split = categorySplitManager.split(domData.object(), categories);
        DomObject limitObject = split.getFirst();

        DomObjectQuery query = queryService.validateAndQueryObject(limitObject);
        query.onToken("limit").object(Triggers.class).set(conditionalEffect::setTriggers);
        DomObject effectsObject = split.getLast();

        Effects effects = new Effects(conditionalEffect.getScope());
        interpreterManager.fillObject(effectsObject, effects);
        conditionalEffect.setEffects(effects);
    }

    public List<CategorySplitDescription> categories() {
        CategorySplitDescription limit = new CategorySplitDescription(
                t -> t.key().val().stringValue().equalsIgnoreCase("limit")
        );
        return List.of(limit);
    }
}
