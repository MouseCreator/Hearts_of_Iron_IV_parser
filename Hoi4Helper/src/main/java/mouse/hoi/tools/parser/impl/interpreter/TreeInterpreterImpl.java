package mouse.hoi.tools.parser.impl.interpreter;

import lombok.RequiredArgsConstructor;
import mouse.hoi.exception.InterpretationException;
import mouse.hoi.tools.parser.annotation.WriteAs;
import mouse.hoi.tools.parser.impl.ast.*;
import mouse.hoi.tools.parser.impl.interpreter.desc.ClassDescription;
import mouse.hoi.tools.parser.impl.interpreter.desc.FieldDescription;
import mouse.hoi.tools.parser.impl.interpreter.desc.ModelClassDescriptor;
import mouse.hoi.tools.parser.protocol.Defaultable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreeInterpreterImpl implements TreeInterpreter {

    private final ModelClassDescriptor descriptor;
    @Override
    public <T> T mapToObject(AbstractSyntaxTree tree, Class<T> clazz) {
        RootNode root = tree.root();
        return createObjectFromNode(root, clazz);
    }

    private <T> T createObjectFromNode(Node node, Class<T> clazz) {
        ClassDescription<T> classData = descriptor.describe(clazz);
        T obj = constuctObject(clazz);
        if (classData.isDefaulted()) {
            callDefault(obj);
        }
        if (node instanceof KeyValueNode keyValueNode) {
            initWithKeyValue(keyValueNode, obj, classData);
        }
        else if (node instanceof ComplexNode complexNode) {
            initWithComplexNode(complexNode, obj, classData);
        }
        return obj;
    }

    private <T> void initWithKeyValue(KeyValueNode keyValueNode, T obj, ClassDescription<T> classData) {
        Node key = keyValueNode.getKey();
        Field keyField = defineFieldByKey(key, classData, obj);
        FieldDescription fieldDescription = descriptor.describeField(keyField);
        Node value = keyValueNode.getValue();
        Object valueObj = defineValue(value, fieldDescription);
        setTo(obj, keyField, valueObj);
    }

    private <T> void setTo(T obj, Field keyField, Object valueObj) {
        keyField.setAccessible(true);
        try {
            keyField.set(obj, valueObj);
        } catch (IllegalAccessException e) {
            throw new InterpretationException(e);
        }
    }

    private Object defineValue(Node value, FieldDescription fieldDescription) {
        if (fieldDescription.isPrimitiveWrap()) {
            return initPrimitiveWrapper(value, fieldDescription);
        }
        if (fieldDescription.isCollectionType()) {
            return initCollectionFiled(value, fieldDescription);
        }
        return null;
    }

    private Object initCollectionFiled(Node value, FieldDescription fieldDescription) {
        return null;
    }

    private Object initPrimitiveWrapper(Node value, FieldDescription fieldDescription) {
        return null;
    }

    private <T> Field defineFieldByKey(Node key, ClassDescription<T> classData, T obj) {
        if (key instanceof IdNode iKey) {
            return fieldFromIdNode(iKey, classData);
        }
        throw new UnsupportedOperationException("No support for scoping in left value: " + key);
    }

    private <T> Field fieldFromIdNode(IdNode iKey, ClassDescription<T> classData) {
        String id = iKey.getId();
        List<Field> fields = classData.fields();
        Optional<Field> fieldOpt = findFieldNamed(id, fields);
        if (fieldOpt.isPresent()) {
            return fieldOpt.get();
        }
        // otherwise check if it is a TAG, state, etc.
        throw new UnsupportedOperationException();
    }

    private Optional<Field> findFieldNamed(String id, List<Field> fields) {
        for (Field f : fields) {
            WriteAs writeAs = f.getAnnotation(WriteAs.class);
            if (writeAs == null) {
                throw new InterpretationException("Field is not annotated with @WriteAs");
            }
            String name = writeAs.value();
            if (name.isEmpty()) {
                name = f.getName();
            }
            if (name.equalsIgnoreCase(id)) {
                return Optional.of(f);
            }
        }
        return Optional.empty();
    }

    private <T> void initWithComplexNode(ComplexNode complexNode, T obj, ClassDescription<T> classData) {
        if (!classData.isComplexInitializable()) {
            throw new InterpretationException("Class " +
                    classData.target() +
                    " is not expected to be initialized with complex node "
                    + complexNode);
        }
    }

    private <T> void callDefault(T obj) {
        Defaultable daf = (Defaultable) obj;
        daf.onDefault();
    }

    private <T> T constuctObject(Class<T> clazz) {
        return null;
    }


}
