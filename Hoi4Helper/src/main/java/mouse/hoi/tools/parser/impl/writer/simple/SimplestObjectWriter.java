package mouse.hoi.tools.parser.impl.writer.simple;

import mouse.hoi.exception.InterpretationException;
import mouse.hoi.exception.PrinterException;
import mouse.hoi.tools.parser.annotation.*;
import mouse.hoi.tools.parser.impl.writer.ObjectWriter;
import mouse.hoi.tools.parser.impl.writer.WriteStyle;
import mouse.hoi.tools.utils.Numbers;
import mouse.hoi.tools.utils.Types;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;

@Service
public class SimplestObjectWriter implements ObjectWriter {
    @Override
    public String write(Object object) {
        SpecialWriter specialWriter = new SpecialWriter();
        writeObj(specialWriter, object);
        return specialWriter.get();
    }

    private void writeObj(SpecialWriter specialWriter, Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            WriteAs annotation = field.getAnnotation(WriteAs.class);
            if (annotation == null) {
                continue;
            }
            if (!specialWriter.isEmpty()) {
                specialWriter.tln();
            }
            String name = annotation.value();
            if (name.isEmpty()) {
                name = field.getName();
            }
            Object fieldValue = getFieldValue(field, object);
            if (fieldValue instanceof Collection<?> c) {
                writeCollection(name, field, specialWriter, c);
            } else if (field.getType().isAnnotationPresent(GameObj.class)) {
                writeBlock(name, specialWriter, field, fieldValue);
            } else { //primitives
                writePrimitive(name, specialWriter, field, fieldValue);
            }
        }
    }

    private void writeBlock(String name, SpecialWriter specialWriter, Field field, Object object) {
        if (object == null) {
            if (field.isAnnotationPresent(SkipIfNull.class) || field.isAnnotationPresent(SkipIfNullOr.class)) {
                return;
            }
            throw new PrinterException("Unexpected null object: " + field);
        }
        StyleHint styleHint = field.getAnnotation(StyleHint.class);
        WriteStyle prevStyle = specialWriter.getWriteStyle();
        boolean hasStyleHint = styleHint != null;
        if (styleHint != null) {
            WriteStyle value = styleHint.value();
            specialWriter.setWriteStyle(value);
        }
        specialWriter.append(name).append(" = {").incrementTab();
        writeObj(specialWriter, object);
        specialWriter.decrementTab().tln().append("}");
        if (hasStyleHint) {
            specialWriter.setWriteStyle(prevStyle);
        }
    }

    private void writePrimitive(String name, SpecialWriter specialWriter, Field field, Object primObj) {
        if (primObj == null) {
            if (field.isAnnotationPresent(SkipIfNull.class) || field.isAnnotationPresent(SkipIfNullOr.class)) {
                return;
            }
            throw new InterpretationException("Unexpected null object " + field);
        }
        String eval = evalPrimitive(field, primObj);
        SkipIf skipIf = field.getAnnotation(SkipIf.class);
        if (skipIf != null && skipIf.value().equals(eval)) {
            return;
        }
        SkipIfNullOr skipIfOr = field.getAnnotation(SkipIfNullOr.class);
        if (skipIfOr != null && skipIfOr.value().equals(eval)) {
            return;
        }
        specialWriter.append(name).append(" = ").append(eval);
    }

    private String evalPrimitive(Field field, Object primObj) {
        String s;
        if (primObj instanceof Double d) {
            Accuracy acc = field.getAnnotation(Accuracy.class);
            int accuracy = acc == null ? 3 : acc.value();
            s = doubleFormatter(d, accuracy);
        }
        else if (primObj instanceof Boolean b) {
            s = Types.mapBoolean(b);
        } else {
            s = String.valueOf(primObj);
        }
        if (field.isAnnotationPresent(Quoted.class)) {
            return "\"" + s + "\"";
        }
        return s;
    }

    private String doubleFormatter(double d, int accuracy) {
        if (Numbers.dEquals(d, 0)) {
            return "0";
        }

        NumberFormat format = NumberFormat.getInstance(Locale.US);
        if (Numbers.dWhole(d)) {
            return String.format(Locale.US, "%.1f", Math.round(d * 10) / 10.0);
        }

        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(accuracy, RoundingMode.HALF_UP);
        return format.format(bd);
    }

    private void writeCollection(String name, Field field, SpecialWriter specialWriter, Collection<?> c) {
        if (field.isAnnotationPresent(SkipIfEmpty.class) && c.isEmpty()) {
            return;
        }
        specialWriter.append(name).append(" = {").incrementTab();
        for (Object o : c) {
            writeObj(specialWriter, o);
        }
        specialWriter.decrementTab().tln().append("}");
    }

    private Object getFieldValue(Field field, Object object) {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
