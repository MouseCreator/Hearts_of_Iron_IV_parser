package mouse.hoi.tools.parser.impl.dom.query;

import lombok.RequiredArgsConstructor;
import mouse.hoi.exception.DomException;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomObject;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomQueryService  {

    private final InterpreterManager interpreterManager;
    public DomObjectQuery queryObject(DomObject domObject) {
        return new DomObjectQuery(domObject, interpreterManager);
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

    public DomListQuery queryList(DomData domData) {
        if (!domData.isList()) {
            throw new DomException("Expected to be a list, but got: " + domData);
        }
        return new DomListQuery(domData.list());
    }
}
