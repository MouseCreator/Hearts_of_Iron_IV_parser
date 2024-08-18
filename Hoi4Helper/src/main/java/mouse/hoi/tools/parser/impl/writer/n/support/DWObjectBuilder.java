package mouse.hoi.tools.parser.impl.writer.n.support;

import mouse.hoi.tools.parser.data.GameDate;
import mouse.hoi.tools.parser.impl.writer.n.DataWriterManager;
import mouse.hoi.tools.parser.impl.writer.n.dw.*;
import mouse.hoi.tools.parser.impl.writer.style.BooleanStyle;
import mouse.hoi.tools.parser.impl.writer.style.DoubleStyle;
import mouse.hoi.tools.parser.impl.writer.style.ObjectStyle;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;

import java.util.Collection;
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
        public void dbl(Supplier<Double> dbl) {
            double d = dbl.get();
            value(new DWDouble(d));
        }

        public void dbl(Supplier<Double> dbl, DoubleStyle style) {
            double d = dbl.get();
            value(new DWDouble(d, style));
        }
        public void object(Supplier<Object> supplier) {
            writerManager.write(supplier.get());
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
    }
}
