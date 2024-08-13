package mouse.hoi.main.states.reader;

import mouse.hoi.exception.ReaderException;
import mouse.hoi.main.states.data.VictoryPoint;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.lr.IntegerValue;
import mouse.hoi.tools.parser.impl.reader.lr.SimpleValue;
import org.springframework.stereotype.Service;

@Service
public class VictoryPointReader implements DataReader<VictoryPoint> {
    @Override
    public Class<VictoryPoint> forType() {
        return VictoryPoint.class;
    }

    @Override
    public void onSimple(VictoryPoint object, SimpleValue simpleValue) {
        if (simpleValue instanceof IntegerValue i) {
            if (object.getProvince()==0) {
                object.setProvince(i.getValue());
                return;
            }
            if (object.getPoints()==0) {
                object.setPoints(i.getValue());
                return;
            }
            throw new ReaderException("Unexpected third integer: " + i.getValue());
        }
        throw new ReaderException("Victory points: only integers are expected. Got " + simpleValue);
    }
}
