package mouse.hoi.tools.parser.impl.dom.active;

import mouse.hoi.tools.parser.impl.writer.dw.*;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;

public class ObjectActiveWriter implements ActiveWriter{
    private ObjectActiveWriter(DWObject object) {
        this.dwObject = object;
    }

    public static ObjectActiveWriter object() {
        return new ObjectActiveWriter(new DWObject(ObjectStyle.DEFAULT));
    }

    public static ObjectActiveWriter oneLine() {
        return new ObjectActiveWriter(new DWObject(ObjectStyle.ONE_LINE));
    }

    @Override
    public DWData getDWData() {
        return dwObject;
    }
    private final DWObject dwObject;

    public void put(String key, String value) {
        DWSimple k = simple(key);
        DWSimple v = simple(value);
        dwObject.add(kv(k, v));
    }

    public void put(String key, ActiveWriter activeWriter) {
        DWSimple k = simple(key);
        DWData v = activeWriter.getDWData();
        dwObject.add(kv(k, v));
    }

    public void put(String key, int value) {
        DWSimple k = simple(key);
        DWSimple v = simple(value);
        dwObject.add(kv(k, v));
    }

    public void put(String key, boolean value) {
        DWSimple k = simple(key);
        DWSimple v = simple(value);
        dwObject.add(kv(k, v));
    }

    private DWSimple simple(boolean value) {
        return new DWBoolean(value);
    }

    private DWSimple simple(int value) {
        return new DWInteger(value);
    }

    private static DWKeyValue kv(DWSimple k, DWData v) {
        return new DWKeyValue(k, v);
    }

    private static DWSimple simple(String key) {
        return new DWString(key);
    }

}
