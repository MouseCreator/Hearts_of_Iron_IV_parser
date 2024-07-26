package mouse.hoi.main.io.writer;

import mouse.hoi.tools.parser.data.DPos;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.StyledDataWriter;
import mouse.hoi.tools.parser.impl.writer.annotation.DefaultWriter;
import mouse.hoi.tools.parser.impl.writer.style.CommonStyles;
import org.springframework.stereotype.Service;

@Service
@DefaultWriter
public class DPosWriter implements StyledDataWriter<DPos, CommonStyles> {
    @Override
    public Class<DPos> forType() {
        return DPos.class;
    }

    @Override
    public void write(SpecialWriter writer, DPos object) {
        writer.key("x").value(object::getX).ln().key("y").value(object::getY);
    }

    @Override
    public CommonStyles forStyle() {
        return CommonStyles.DEFAULT;
    }
}
