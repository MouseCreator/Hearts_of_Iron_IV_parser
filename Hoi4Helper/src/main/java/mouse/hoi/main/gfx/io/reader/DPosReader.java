package mouse.hoi.main.gfx.io.reader;

import lombok.AllArgsConstructor;
import mouse.hoi.tools.parser.data.DPos;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.query.DomObjectQuery;
import mouse.hoi.tools.parser.impl.dom.query.DomQueryService;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DPosReader implements DataReader<DPos> {

    private final DomQueryService queryService;
    @Override
    public Class<DPos> forType() {
        return DPos.class;
    }

    @Override
    public DPos read(DomData domData) {
        DPos dPos = new DPos();
        DomObjectQuery domObjectQuery = queryService.validateAndQueryObject(domData);
        domObjectQuery.requireToken("x").simple().setDouble(dPos::setX);
        domObjectQuery.requireToken("y").simple().setDouble(dPos::setY);
        return dPos;
    }
}
