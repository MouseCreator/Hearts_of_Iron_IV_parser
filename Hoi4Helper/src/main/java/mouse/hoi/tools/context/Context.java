package mouse.hoi.tools.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Context {
    public static ApplicationContext get() {
        return new AnnotationConfigApplicationContext(SpringConfig.class);
    }
}
