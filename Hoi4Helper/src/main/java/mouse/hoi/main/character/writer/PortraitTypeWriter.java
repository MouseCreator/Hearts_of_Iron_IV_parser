package mouse.hoi.main.character.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.PortraitType;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import mouse.hoi.tools.utils.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortraitTypeWriter implements DataWriter<PortraitType> {
    private final WriterSupport writerSupport;
    @Override
    public Class<PortraitType> forType() {
        return PortraitType.class;
    }

    @Override
    public DWData write(PortraitType object, ObjectStyle style) {
        DWObjectBuilder build = writerSupport.build(style);
        NotNull.supply(object::getLarge, s -> build.key("large").string(s));
        NotNull.supply(object::getSmall, s -> {
            if (s.startsWith("GFX")) {
                build.key("small").string(s, StringStyle.QUOTED);
            } else {
                build.key("small").string(s);
            }
        });
        return build.get();
    }
}
