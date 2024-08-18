package mouse.hoi.tools.parser.impl.dom.query;

import mouse.hoi.exception.DomException;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomObject;
import org.springframework.stereotype.Service;

@Service
public class DomQueryService {
    public DomObjectQuery queryObject(DomObject domObject) {
        return new DomObjectQuery(domObject);
    }

    public void validateObject(DomData domData) {
        if (!domData.isObject()) {
            throw new DomException("Expected to be an object, but got: " + domData);
        }
    }

    public DomObjectQuery validateAndQueryObject(DomData domData) {
        validateObject(domData);
        return queryObject(domData.object());
    }
}
