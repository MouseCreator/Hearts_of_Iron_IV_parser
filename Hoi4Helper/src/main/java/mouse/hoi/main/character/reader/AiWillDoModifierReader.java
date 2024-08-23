package mouse.hoi.main.character.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.AiWillDoModifier;
import mouse.hoi.main.common.data.scope.CountryScope;
import mouse.hoi.main.common.data.trigger.Triggers;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomObject;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.split.SplitByKeys;
import mouse.hoi.tools.parser.impl.split.SplitKeys;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiWillDoModifierReader implements DataReader<AiWillDoModifier> {

    private final SplitByKeys splitService;
    private final InterpreterManager interpreterManager;
    private final DomQueryService domQueryService;
    @Override
    public Class<AiWillDoModifier> forType() {
        return AiWillDoModifier.class;
    }

    @Override
    public AiWillDoModifier read(DomData domData) {
        AiWillDoModifier aiWillDoModifier = new AiWillDoModifier();
        domQueryService.validateObject(domData);
        List<DomObject> split = splitService.split(domData.object(), List.of(SplitKeys.strings(List.of("factor"))));
        DomObject math = split.get(0);
        DomObject triggers = split.get(1);
        Triggers triggersObj = new Triggers(new CountryScope());
        interpreterManager.fillObject(triggers, triggersObj);
        DomObjectQuery query = domQueryService.validateAndQueryObject(math);
        query.onToken("factor").doublev().setOrSkip(aiWillDoModifier::setFactor);
        aiWillDoModifier.setConditions(triggersObj);
        return aiWillDoModifier;
    }
}
