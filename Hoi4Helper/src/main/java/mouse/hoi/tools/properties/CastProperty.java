package mouse.hoi.tools.properties;

import mouse.hoi.exception.PropertyException;

public class CastProperty {
    public static <T> T cast(String str, Class<T> castTo) {
        Object obj = propertyType(str, castTo);
        return castObject(obj, castTo);
    }


    private static <T> T castObject(Object obj, Class<T> castTo) {
        try {
            return castTo.cast(obj);
        } catch (Exception e) {
            throw new PropertyException(e);
        }
    }

    private static Object propertyType(String argument, Class<?> type) {
        if (type == Double.class || type == double.class) {
            return Double.parseDouble(argument);
        } else if (type == Integer.class || type == int.class) {
            return Integer.parseInt(argument);
        } else if (type == String.class) {
            return argument;
        } else if (type == Float.class || type == float.class) {
            return Float.parseFloat(argument);
        } else if (type == Boolean.class || type == boolean.class) {
            return argument.equals("true") ? Boolean.TRUE : argument.equals("false") ? false : null;
        }  else if (type == Byte.class || type == byte.class) {
           return Byte.parseByte(argument);
        } else if (type == Short.class || type == short.class) {
            return Short.parseShort(argument);
        }
        throw new PropertyException("Unknown property type");
    }

    public static Object asObject(String val, Class<?> clazz) {
        return propertyType(val, clazz);
    }
}
