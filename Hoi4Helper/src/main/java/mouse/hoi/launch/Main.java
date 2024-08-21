package mouse.hoi.launch;

import mouse.hoi.main.states.service.resource.ResourcesService;
import mouse.hoi.tools.context.App;

public class Main {
    public static void main(String[] args) {
        App.useService(ResourcesService.class);
    }
}
