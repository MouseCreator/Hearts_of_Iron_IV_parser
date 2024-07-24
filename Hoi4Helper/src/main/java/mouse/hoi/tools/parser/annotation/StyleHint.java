package mouse.hoi.tools.parser.annotation;

import mouse.hoi.tools.parser.impl.writer.WriteStyle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StyleHint {
    WriteStyle value();
}
