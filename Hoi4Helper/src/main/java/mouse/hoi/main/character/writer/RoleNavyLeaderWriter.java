package mouse.hoi.main.character.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.RoleNavyLeader;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ListStyle;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleNavyLeaderWriter implements DataWriter<RoleNavyLeader> {

    private final WriterSupport writerSupport;
    @Override
    public Class<RoleNavyLeader> forType() {
        return RoleNavyLeader.class;
    }

    @Override
    public DWData write(RoleNavyLeader object, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        b.key("traits").stringList(object::getTraits, ListStyle.ONE_LINE);
        b.key("skill").integer(object::getSkill);
        b.key("attack_skill").integer(object::getAttackSkill);
        b.key("defense_skill").integer(object::getDefenceSkill);
        b.key("maneuvering_skill").integer(object::getManeuveringSkill);
        b.key("coordination_skill").integer(object::getCoordinationSkill);
        b.key("legacy_id").integer(object::getLegacyId);
        return b.get();
    }
}
