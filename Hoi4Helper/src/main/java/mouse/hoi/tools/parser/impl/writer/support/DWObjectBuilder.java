package mouse.hoi.tools.parser.impl.writer.support;

import mouse.hoi.tools.parser.data.GameDate;
import mouse.hoi.tools.parser.impl.writer.DataWriterManager;
import mouse.hoi.tools.parser.impl.writer.dw.*;
import mouse.hoi.tools.parser.impl.writer.style.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class DWObjectBuilder {
    private final DWObject object;
    private final DataWriterManager writerManager;
    public DWObjectBuilder(DataWriterManager writerManager) {
        this.writerManager = writerManager;
        object = new DWObject();
    }
    public DWObjectBuilder(ObjectStyle style, DataWriterManager writerManager) {
        object = new DWObject(style);
        this.writerManager = writerManager;
    }

    public TargetBuilder key(String key) {
        return new TargetBuilder(new DWString(key));
    }
    public TargetBuilder key(int i) {
        return new TargetBuilder(new DWInteger(i));
    }
    public TargetBuilder key(String key, StringStyle style) {
        return new TargetBuilder(new DWString(key, style));
    }
    public TargetBuilder key(GameDate date) {
        return new TargetBuilder(new DWDate(date));
    }

    public DWData get() {
        return object;
    }

    public ListBuilder listAll(String key) {
        return new ListBuilder(new DWString(key));
    }

    public void embedded(Supplier<Object> supplier) {
        Object obj = supplier.get();
        DWData dwData = writerManager.write(obj, ObjectStyle.EMBEDDED);
        object.add(new DWEmbedded(dwData));
    }

    public void skipLine() {
        object.add(new DWEmpty());
    }

    public class ListBuilder {
        private final DWSimple dwSimple;

        public ListBuilder(DWSimple dwSimple) {
            this.dwSimple = dwSimple;
        }
        public void objects(Supplier<Collection<?>> supplier) {
            Collection<?> c = supplier.get();
            for (Object obj : c) {
                DWData val = writerManager.write(obj);
                DWKeyValue keyValue = new DWKeyValue(dwSimple, val);
                object.add(keyValue);
            }
        }

        public void strings(Supplier<Collection<String>> supplier) {
            Collection<String> strings = supplier.get();
            for (String s : strings) {
                DWSimple simple = new DWString(s);
                DWKeyValue keyValue = new DWKeyValue(dwSimple, simple);
                object.add(keyValue);
            }
        }

        public void objects(Supplier<Collection<?>> supplier, ObjectStyle objectStyle) {
            Collection<?> c = supplier.get();
            for (Object obj : c) {
                DWData val = writerManager.write(obj, objectStyle);
                DWKeyValue keyValue = new DWKeyValue(dwSimple, val);
                object.add(keyValue);
            }
        }
    }

    public class TargetBuilder {
        private final DWSimple key;
        public TargetBuilder(DWSimple dwSimple) {
            this.key = dwSimple;
        }
        private DWKeyValue kv(DWData target) {
            return new DWKeyValue(key, target);
        }
        public void value(DWData data) {
            DWKeyValue kv = kv(data);
            object.add(kv);
        }
        public void string(Supplier<String> supplier) {
            String string = supplier.get();
            value(new DWString(string));
        }
        public void string(Supplier<String> supplier, StringStyle style) {
            String string = supplier.get();
            value(new DWString(string, style));
        }
        public void integer(Supplier<Integer> supplier) {
            int value = supplier.get();
            value(new DWInteger(value));
        }
        public void integer(int i) {
            value(new DWInteger(i));
        }
        public void dbl(Supplier<Double> dbl) {
            double d = dbl.get();
            value(new DWDouble(d));
        }

        public void dbl(Supplier<Double> dbl, DoubleStyle style) {
            double d = dbl.get();
            value(new DWDouble(d, style));
        }
        public void object(Supplier<Object> supplier) {
            DWData written = writerManager.write(supplier.get());
            value(written);
        }
        public void object(Supplier<Object> supplier, ObjectStyle style) {
            writerManager.write(supplier.get(), style);
        }
        public void date(Supplier<GameDate> supplier) {
            value(new DWDate(supplier.get()));
        }
        public void bool(Supplier<Boolean> supplier) {
            value(new DWBoolean(supplier.get()));
        }
        public void bool(Supplier<Boolean> supplier, BooleanStyle style) {
            value(new DWBoolean(supplier.get(), style));
        }

        public void string(String s, StringStyle stringStyle) {
            value(new DWString(s, stringStyle));
        }
        public void string(String s) {
            value(new DWString(s));
        }

        public void bool(Boolean b) {
            value(new DWBoolean(b));
        }
        public void bool(Boolean b, BooleanStyle style) {
            value(new DWBoolean(b, style));
        }

        public void objectRaw(Object object) {
            DWData write = writerManager.write(object);
            value(write);
        }

        public void objectRaw(Object object, ObjectStyle style) {
            DWData write = writerManager.write(object, style);
            value(write);
        }

        public void stringList(List<String> strings, ListStyle style) {
            List<DWSimple> list = new ArrayList<>();
            for (String string : strings) {
                DWString dwString = new DWString(string);
                list.add(dwString);
            }
            DWList dwList = new DWList(list, style);
            value(dwList);
        }


        public void integerList(Supplier<List<Integer>> supplier, ListStyle style) {
            List<DWSimple> list = new ArrayList<>();
            List<Integer> integers = supplier.get();
            for (int i : integers) {
                DWInteger dwInteger = new DWInteger(i);
                list.add(dwInteger);
            }
            DWList dwList = new DWList(list, style);
            value(dwList);
        }
    }
}
