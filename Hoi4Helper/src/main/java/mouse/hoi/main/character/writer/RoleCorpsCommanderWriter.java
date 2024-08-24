package mouse.hoi.main.character.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.RoleCorpsCommander;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ListStyle;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleCorpsCommanderWriter implements DataWriter<RoleCorpsCommander> {

    private final WriterSupport writerSupport;
    @Override
    public Class<RoleCorpsCommander> forType() {
        return RoleCorpsCommander.class;
    }

    @Override
    public DWData write(RoleCorpsCommander object, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        b.key("traits").stringList(object::getTraits, ListStyle.ONE_LINE);
        b.key("skill").integer(object::getSkill);
        b.key("attack_skill").integer(object::getAttackSkill);
        b.key("defense_skill").integer(object::getDefenceSkill);
        b.key("planning_skill").integer(object::getPlanningSKill);
        b.key("logistics_skill").integer(object::getLogisticsSkill);
        b.key("legacy_id").integer(object::getLegacyId);
        return b.get();
    }
}