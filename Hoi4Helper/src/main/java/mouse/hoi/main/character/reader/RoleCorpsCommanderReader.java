package mouse.hoi.main.character.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.RoleCorpsCommander;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleCorpsCommanderReader implements DataReader<RoleCorpsCommander> {

    private final DomQueryService queryService;
    @Override
    public Class<RoleCorpsCommander> forType() {
        return RoleCorpsCommander.class;
    }

    @Override
    public RoleCorpsCommander read(DomData domData) {
        RoleCorpsCommander commander = new RoleCorpsCommander();
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        query.onToken("traits").stringList().setOrSkip(commander::setTraits);
        query.requireToken("skill").integer().set(commander::setSkill);
        query.requireToken("attack_skill").integer().set(commander::setAttackSkill);
        query.requireToken("defense_skill").integer().set(commander::setDefenceSkill);
        query.requireToken("planning_skill").integer().set(commander::setPlanningSKill);
        query.requireToken("logistics_skill").integer().set(commander::setLogisticsSkill);
        query.onToken("legacy_id").integer().setOrDefault(commander::setLegacyId, -1);
        return commander;
    }
}
