package mouse.hoi.launch;

import mouse.hoi.main.states.service.vp.VictoryPointsService;
import mouse.hoi.tools.context.App;

public class VPMain {
    public static void main(String[] args) {
        App.useService(VictoryPointsService.class);
    }
}
