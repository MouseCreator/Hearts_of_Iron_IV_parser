package mouse.hoi.main.states.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.VictoryPoint;
import mouse.hoi.tools.parser.impl.writer.DataWriter;
import mouse.hoi.tools.parser.impl.writer.dw.DWData;
import mouse.hoi.tools.parser.impl.writer.style.ListStyle;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.support.WriterSupport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VictoryPointWriter implements DataWriter<VictoryPoint> {

    private final WriterSupport writerSupport;
    @Override
    public Class<VictoryPoint> forType() {
        return VictoryPoint.class;
    }

    @Override
    public DWData write(VictoryPoint victoryPoint, ObjectStyle style) {
        List<Integer> list = List.of(victoryPoint.getProvince(), victoryPoint.getPoints());
        return writerSupport.list(ListStyle.ONE_LINE).ofIntegers(list);
    }
}
