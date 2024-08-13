package mouse.hoi.launch;

import mouse.hoi.main.states.service.assign.StateAssignerService;
import mouse.hoi.tools.context.Context;
import org.springframework.context.ApplicationContext;

public class AssignMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = Context.get();
        StateAssignerService service = applicationContext.getBean(StateAssignerService.class);
        service.assign();
    }
}
