package mouse.hoi.main.character.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.RoleNavyLeader;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleNavyLeaderReader implements DataReader<RoleNavyLeader> {
    private final DomQueryService queryService;
    @Override
    public Class<RoleNavyLeader> forType() {
        return RoleNavyLeader.class;
    }

    @Override
    public RoleNavyLeader read(DomData domData) {
        RoleNavyLeader navyLeader = new RoleNavyLeader();
        DomObjectQuery query = queryService.validateAndQueryObject(domData);
        query.onToken("traits").stringList().setOrSkip(navyLeader::setTraits);
        query.requireToken("skill").integer().set(navyLeader::setSkill);
        query.requireToken("attack_skill").integer().set(navyLeader::setAttackSkill);
        query.requireToken("defense_skill").integer().set(navyLeader::setDefenceSkill);
        query.requireToken("maneuvering_skill").integer().set(navyLeader::setManeuveringSkill);
        query.requireToken("coordination_skill").integer().set(navyLeader::setCoordinationSkill);
        query.onToken("legacy_id").integer().setOrDefault(navyLeader::setLegacyId, -1);
        return navyLeader;
    }
}
