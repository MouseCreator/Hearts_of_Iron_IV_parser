package mouse.hoi.launch;

import mouse.hoi.main.gfx.service.SpriteTypesService;
import mouse.hoi.tools.context.Context;
import org.springframework.context.ApplicationContext;

public class GFXMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = Context.get();
        SpriteTypesService service = applicationContext.getBean(SpriteTypesService.class);
        service.synchronize();
    }
}
