package mouse.hoi.main.character.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.RoleCountryLeader;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ListStyle;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleCountryLeaderWriter implements DataWriter<RoleCountryLeader> {

    private final WriterSupport writerSupport;
    @Override
    public Class<RoleCountryLeader> forType() {
        return RoleCountryLeader.class;
    }

    @Override
    public DWData write(RoleCountryLeader object, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        b.key("traits").stringList(object::getTraits, ListStyle.ONE_LINE);
        b.key("ideology").string(object::getIdeology);
        b.key("expire").string(object::getExpire, StringStyle.QUOTED);
        b.key("id").integer(object::getId);
        return b.get();
    }
}
