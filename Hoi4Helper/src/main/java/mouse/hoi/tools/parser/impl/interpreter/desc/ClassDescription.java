package mouse.hoi.tools.parser.impl.interpreter.desc;

import lombok.Data;
import lombok.Setter;
import mouse.hoi.tools.parser.annotation.OfComplexNode;
import mouse.hoi.tools.parser.annotation.WriteAs;
import mouse.hoi.tools.parser.protocol.Defaultable;

import java.lang.reflect.Field;
import java.util.*;
@Data
public class ClassDescription<T> {
    @Setter
    private Class<T> target;
    private Set<Class<?>> interfaces;
    public boolean isComplexInitializable() {
        return target.isAnnotationPresent(OfComplexNode.class);
    }
    public Class<T> target() {
        return target;
    }

    public boolean implementsInterface(Class<?> interfaceClass) {
        return interfaces.contains(interfaceClass);
    }
    public List<Field> fields() {
        Field[] declaredFields = target.getDeclaredFields();
        return Arrays.stream(declaredFields).filter(d -> d.isAnnotationPresent(WriteAs.class)).toList();
    }

    public void setInterfaces(Collection<Class<?>> interfaces) {
        this.interfaces = new HashSet<>(interfaces);
    }

    public boolean isDefaulted() {
        return implementsInterface(Defaultable.class);
    }
}
