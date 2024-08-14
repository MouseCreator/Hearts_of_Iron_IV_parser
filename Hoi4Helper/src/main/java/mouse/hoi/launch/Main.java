package mouse.hoi.launch;

import mouse.hoi.main.states.service.naval.NavalBaseService;
import mouse.hoi.tools.context.App;

public class Main {
    public static void main(String[] args) {
        App.useService(NavalBaseService.class);
    }
}
