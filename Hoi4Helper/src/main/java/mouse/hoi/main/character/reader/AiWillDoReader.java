package mouse.hoi.main.character.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.AiWillDo;
import mouse.hoi.main.character.data.AiWillDoModifier;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AiWillDoReader implements DataReader<AiWillDo> {

    private final DomQueryService domQueryService;
    @Override
    public Class<AiWillDo> forType() {
        return AiWillDo.class;
    }

    @Override
    public AiWillDo read(DomData domData) {
        AiWillDo aiWillDo = new AiWillDo();
        DomObjectQuery query = domQueryService.validateAndQueryObject(domData);
        query.requireToken("factor").doublev().set(aiWillDo::setFactor);
        query.onToken("modifier").object(AiWillDoModifier.class).pushOrSkip(aiWillDo::getModifierList);
        return aiWillDo;
    }
}
