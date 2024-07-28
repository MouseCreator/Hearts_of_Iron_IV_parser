package mouse.hoi.tools.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Context {

    private static ApplicationContext context = null;
    public static ApplicationContext get() {
        if (context == null) {
            context = new AnnotationConfigApplicationContext(SpringConfig.class);
        }
        return context;
    }
}
