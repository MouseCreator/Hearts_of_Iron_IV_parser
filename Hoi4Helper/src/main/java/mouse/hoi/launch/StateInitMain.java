package mouse.hoi.launch;

import mouse.hoi.main.states.service.init.StateInitService;
import mouse.hoi.tools.context.Context;
import org.springframework.context.ApplicationContext;

public class StateInitMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = Context.get();
        StateInitService service = applicationContext.getBean(StateInitService.class);
        service.initAll();
    }
}
