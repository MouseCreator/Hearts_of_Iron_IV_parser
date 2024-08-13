package mouse.hoi.launch;

import mouse.hoi.main.states.service.err.FixStatesService;
import mouse.hoi.tools.context.App;

public class ErrorsFixMain {
    public static void main(String[] args) {
        App.useService(FixStatesService.class);
    }
}
