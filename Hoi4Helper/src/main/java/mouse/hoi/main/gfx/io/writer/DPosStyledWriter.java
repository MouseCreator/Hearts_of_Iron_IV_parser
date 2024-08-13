package mouse.hoi.main.gfx.io.writer;

import lombok.AllArgsConstructor;
import mouse.hoi.tools.parser.data.DPos;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import mouse.hoi.tools.parser.impl.writer.StyledDataWriter;
import mouse.hoi.tools.parser.impl.writer.style.CommonStyles;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DPosStyledWriter implements StyledDataWriter<DPos, CommonStyles> {
    @Override
    public Class<DPos> forType() {
        return DPos.class;
    }

    @Override
    public void write(SpecialWriter writer, DPos object) {
        writer.space().key("x").valueDouble(object::getX).space().key("y").valueDouble(object::getY).space();
    }

    @Override
    public CommonStyles forStyle() {
        return CommonStyles.SIMPLE;
    }
}
