package mouse.hoi.main.states.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.Resources;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResourcesWriter implements DataWriter<Resources> {

    private final WriterSupport support;
    @Override
    public Class<Resources> forType() {
        return Resources.class;
    }

    @Override
    public DWData write(Resources resources, ObjectStyle style) {
        DWObjectBuilder b = support.build(style);
        for (String type : resources.keys()) {
            b.key(type).integer(resources.getAmount(type));
        }
        return b.get();
    }
}
