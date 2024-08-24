package mouse.hoi.main.common.data.trigger;

import mouse.hoi.main.common.data.scope.ScopeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UseTrigger {
    String key();
    ScopeEnum scopes();
}
