package mouse.hoi.main.character.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.RoleAdvisor;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.dw.DWList;
import mouse.hoi.tools.parser.impl.writer.dw.DWSimple;
import mouse.hoi.tools.parser.impl.writer.dw.DWString;
import mouse.hoi.tools.parser.impl.writer.style.ListStyle;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import mouse.hoi.tools.utils.NotNull;
import mouse.hoi.tools.utils.TestIf;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleAdvisorWriter implements DataWriter<RoleAdvisor> {

    private final WriterSupport writerSupport;
    @Override
    public Class<RoleAdvisor> forType() {
        return RoleAdvisor.class;
    }

    @Override
    public DWData write(RoleAdvisor object, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        TestIf.supply(object::getCost, t -> t != 150).then(i -> b.key("cost").integer(i));
        b.key("slot").string(object::getSlot);
        NotNull.supply(object::getLedger, s -> b.key("ledger").string(s));
        NotNull.supply(object::getToken, s -> b.key("idea_token").string(s));
        b.key("allowed").value(asBlock(object.getAllowed()));
        b.key("available").value(asBlock(object.getAvailable()));
        b.key("ai_will_do").object(object::getAiWillDo);
        return b.get();
    }

    private DWData asBlock(List<String> allowed) {
        List<DWSimple> simples = new ArrayList<>();
        for (String t : allowed) {
            simples.add(new DWString(t));
        }
        return new DWList(simples, ListStyle.MULTI_LINE);
    }
}
