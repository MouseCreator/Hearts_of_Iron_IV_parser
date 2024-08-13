package mouse.hoi.tools.context;

import org.springframework.context.ApplicationContext;

public class App {
    public static void useService(Class<? extends AppService> clazz) {
        ApplicationContext applicationContext = Context.get();
        AppService bean = applicationContext.getBean(clazz);
        bean.start();
    }
}
