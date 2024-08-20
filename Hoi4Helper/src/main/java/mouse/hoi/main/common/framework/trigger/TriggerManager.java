package mouse.hoi.main.common.framework.trigger;

import mouse.hoi.exception.DomException;
import mouse.hoi.main.common.data.trigger.Trigger;
import mouse.hoi.tools.parser.impl.dom.DomKV;
import org.springframework.stereotype.Service;

@Service
public class TriggerManager {
    public Trigger createTrigger(DomKV d) {
        throw new DomException("Unsupported operation: TRIGGER MANAGER");
    }
}
