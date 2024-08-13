package mouse.hoi.launch;

import mouse.hoi.main.states.service.manpower.StateManpowerService;
import mouse.hoi.tools.context.App;

public class ManpowerMain {
    public static void main(String[] args) {
        App.useService(StateManpowerService.class);
    }
}
