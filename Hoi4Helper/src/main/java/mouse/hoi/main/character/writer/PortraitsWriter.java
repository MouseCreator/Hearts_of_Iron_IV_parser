package mouse.hoi.main.character.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.character.data.PortraitType;
import mouse.hoi.main.character.data.Portraits;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortraitsWriter implements DataWriter<Portraits> {

    private final WriterSupport writerSupport;
    @Override
    public Class<Portraits> forType() {
        return Portraits.class;
    }

    @Override
    public DWData write(Portraits object, ObjectStyle style) {
        DWObjectBuilder build = writerSupport.build(style);
        List<PortraitType> portraitTypeList = object.getPortraitTypeList();
        for (PortraitType type : portraitTypeList) {
            build.key(type.getName()).objectRaw(type);
        }
        return build.get();
    }
}
