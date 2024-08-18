package mouse.hoi.main.states.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.main.states.data.VictoryPoint;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VictoryPointWriter implements DataWriter<VictoryPoint> {
    @Override
    public Class<VictoryPoint> forType() {
        return VictoryPoint.class;
    }

    @Override
    public void write(SpecialWriter writer, VictoryPoint object) {
        writer.beginObj().write(object.getProvince()).space().write(object.getPoints()).ln().endObj();
    }
}
