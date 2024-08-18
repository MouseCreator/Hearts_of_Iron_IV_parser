package mouse.hoi.main.gfx.io.writer;

import lombok.AllArgsConstructor;
import mouse.hoi.tools.parser.data.DPos;
import mouse.hoi.tools.parser.impl.writer.n.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.n.support.DWObjectBuilder;
import mouse.hoi.tools.parser.impl.writer.n.support.WriterSupport;
import mouse.hoi.tools.parser.impl.writer.n.DataWriter;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DPosStyledWriter implements DataWriter<DPos> {
    @Override
    public Class<DPos> forType() {
        return DPos.class;
    }

    private final WriterSupport writerSupport;

    @Override
    public DWData write(DPos pos, ObjectStyle style) {
        DWObjectBuilder b = writerSupport.build(style);
        b.key("x").dbl(pos::getX);
        b.key("y").dbl(pos::getY);
        return b.get();
    }
}
