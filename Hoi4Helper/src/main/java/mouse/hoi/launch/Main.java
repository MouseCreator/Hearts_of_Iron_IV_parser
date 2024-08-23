package mouse.hoi.launch;

import mouse.hoi.main.gfx.service.SpriteTypesService;
import mouse.hoi.tools.context.App;

public class Main {
    public static void main(String[] args) {
        App.useService(SpriteTypesService.class);
    }
}
