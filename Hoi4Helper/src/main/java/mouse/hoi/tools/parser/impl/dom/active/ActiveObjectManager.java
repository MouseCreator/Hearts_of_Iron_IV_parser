package mouse.hoi.tools.parser.impl.dom.active;

import mouse.hoi.tools.parser.impl.dom.DomData;
import org.springframework.stereotype.Service;

@Service
public class ActiveObjectManager {
    public ActiveObject createActiveObject(DomData domData) {
        return new ActiveObject(domData);
    }
}
