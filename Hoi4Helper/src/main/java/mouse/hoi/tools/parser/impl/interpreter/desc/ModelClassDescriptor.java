package mouse.hoi.tools.parser.impl.interpreter.desc;

import java.lang.reflect.Field;

public interface ModelClassDescriptor {
    <T> ClassDescription<T> describe(Class<T> clazz);
    FieldDescription describeField(Field keyField);
}
