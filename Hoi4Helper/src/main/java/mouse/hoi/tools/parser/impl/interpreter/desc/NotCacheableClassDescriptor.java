package mouse.hoi.tools.parser.impl.interpreter.desc;

import mouse.hoi.exception.InterpretationException;
import mouse.hoi.tools.parser.data.primitive.TBoolean;
import mouse.hoi.tools.parser.data.primitive.TDouble;
import mouse.hoi.tools.parser.data.primitive.TInt;
import mouse.hoi.tools.parser.data.primitive.TString;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class NotCacheableClassDescriptor implements ModelClassDescriptor {
    @Override
    public <T> ClassDescription<T> describe(Class<T> clazz) {
        ClassDescription<T> classDescription = new ClassDescription<>();
        classDescription.setTarget(clazz);
        Set<Class<?>> classes = allInterfaces(clazz);
        classDescription.setInterfaces(classes);

        return classDescription;
    }

    private Set<Class<?>> allInterfaces(Class<?> clazz) {

        Set<Class<?>> res = new HashSet<>();
        Class<?>[] interfaces = clazz.getInterfaces();

        if (interfaces.length > 0) {
            res.addAll(Arrays.asList(interfaces));

            for (Class<?> i : interfaces) {
                res.addAll(allInterfaces(i));
            }
        }

        return res;
    }

    @Override
    public FieldDescription describeField(Field field) {
        FieldDescription description = new FieldDescription();
        description.setTarget(field);
        Class<?> type = field.getType();
        validateType(type);
        description.setType(type);
        boolean isWrap = isPrimitiveWrap(type);
        description.setPrimitiveWrap(isWrap);
        return description;
    }

    private boolean isPrimitiveWrap(Class<?> type) {
        return List.of(TInt.class, TDouble.class, TString.class, TBoolean.class).contains(type);
    }


    private void validateType(Class<?> type) {
        if (type.isPrimitive()) {
            throw new InterpretationException("Not safe to use primitive type: " + type);
        }
    }
}
