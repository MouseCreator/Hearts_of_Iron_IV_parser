package mouse.hoi.tools.parser.impl.dom.query;

import mouse.hoi.exception.DomException;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomObject;
import mouse.hoi.tools.parser.impl.dom.DomSimple;

import java.util.List;

public class DomObjectQuery {
    private final DomObject domObject;

    public DomObjectQuery(DomObject domObject) {
        this.domObject = domObject;
    }

    public DomQueryResult onToken(String string) {
        DomSimple domSimple = new DomSimple(SV.string(string));
        List<DomData> dataByKey = domObject.getIgnoreCase(domSimple);
        return new DomQueryResult(dataByKey);
    }
    public DomQueryResult requireToken(String string) {
        DomSimple domSimple = new DomSimple(SV.string(string));
        List<DomData> dataByKey = domObject.getIgnoreCase(domSimple);
        return new DomQueryResult(dataByKey);
    }

    private List<DomData> getOrThrow(String string, DomSimple domSimple) {
        List<DomData> dataByKey = domObject.getIgnoreCase(domSimple);
        if (dataByKey == null) {
            throw new DomException("Expected to get one value by key '" + string + "' but got none");
        }
        return dataByKey;
    }
}
