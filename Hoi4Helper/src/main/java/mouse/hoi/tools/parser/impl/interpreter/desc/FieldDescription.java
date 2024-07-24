package mouse.hoi.tools.parser.impl.interpreter.desc;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class FieldDescription {
    private boolean primitiveWrap;
    private Class<?> type;
    private Field target;
    private boolean collectionType;
    private Class<?> primitive;
    private Class<?> generic;
}
