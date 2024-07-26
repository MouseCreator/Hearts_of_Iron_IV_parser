package mouse.hoi.main.io.reader;

import lombok.AllArgsConstructor;
import mouse.hoi.tools.parser.data.DPos;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DPosReader implements DataReader<DPos> {

    private final Readers readers;
    @Override
    public Class<DPos> forType() {
        return DPos.class;
    }

    @Override
    public void onKeyValue(DPos object, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues().with(leftValue, rightValue)
                .onToken("x").setDouble(object::setX)
                .onToken("y").setDouble(object::setY)
                .orElseThrow();
    }
}
