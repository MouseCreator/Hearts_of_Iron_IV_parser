package mouse.hoi.main.character.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.AiWillDo;
import mouse.hoi.main.character.data.RoleAdvisor;
import mouse.hoi.main.common.data.trigger.scoped.Triggers;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleAdvisorReader implements DataReader<RoleAdvisor> {

    private final DomQueryService queryService;
    @Override
    public Class<RoleAdvisor> forType() {
        return RoleAdvisor.class;
    }

    @Override
    public RoleAdvisor read(DomData domData) {
        RoleAdvisor advisor = new RoleAdvisor();
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        query.onToken("cost").integer().setOrDefault(advisor::setCost, 150);
        query.onToken("slot").string().setOrSkip(advisor::setSlot);
        query.onToken("ledger").string().setOrSkip(advisor::setLedger);
        query.requireToken("idea_token").string().set(advisor::setToken);
        //query.onToken("allowed").object(Triggers.class).setOrSkip(advisor::setAllowed);
        //query.onToken("available").object(Triggers.class).setOrSkip(advisor::setAvailable);
        query.onToken("traits").stringList().setOrSkip(advisor::setTraits);
        query.onToken("ai_will_do").object(AiWillDo.class).setOrSkip(advisor::setAiWillDo);

        return advisor;
    }
}
