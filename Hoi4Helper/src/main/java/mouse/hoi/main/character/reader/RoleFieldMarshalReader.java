package mouse.hoi.main.character.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.RoleCorpsCommander;
import mouse.hoi.main.character.data.RoleFieldMarshal;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleFieldMarshalReader implements DataReader<RoleFieldMarshal> {
    private final DomQueryService queryService;
    @Override
    public Class<RoleFieldMarshal> forType() {
        return RoleFieldMarshal.class;
    }

    @Override
    public RoleFieldMarshal read(DomData domData) {
        RoleFieldMarshal fieldMarshal = new RoleFieldMarshal();
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        query.onToken("traits").stringList().setOrSkip(fieldMarshal::setTraits);
        query.requireToken("skill").integer().set(fieldMarshal::setSkill);
        query.requireToken("attack_skill").integer().set(fieldMarshal::setAttackSkill);
        query.requireToken("defense_skill").integer().set(fieldMarshal::setDefenceSkill);
        query.requireToken("planning_skill").integer().set(fieldMarshal::setPlanningSKill);
        query.requireToken("logistics_skill").integer().set(fieldMarshal::setLogisticsSkill);
        query.onToken("legacy_id").integer().setOrDefault(fieldMarshal::setLegacyId, -1);
        return fieldMarshal;
    }
}
