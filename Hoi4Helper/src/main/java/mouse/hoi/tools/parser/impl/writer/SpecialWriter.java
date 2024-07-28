package mouse.hoi.tools.parser.impl.writer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.parser.impl.writer.style.BooleanStyle;
import mouse.hoi.tools.parser.impl.writer.style.CommonStyles;
import mouse.hoi.tools.parser.impl.writer.style.DoubleStyle;
import mouse.hoi.tools.parser.impl.writer.style.StringStyle;
import mouse.hoi.tools.utils.Types;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SpecialWriter {

    final WriterHelper helper;
    private final StringBuilder stringBuilder;
    private int tabsLevel;
    private boolean afterLn;
    public SpecialWriter(WriterHelper helper) {
        this.helper = helper;
        stringBuilder = new StringBuilder();
        tabsLevel = 0;
        afterLn = false;
    }

    private void onBegin() {
        if (afterLn) {
            afterLn = false;
            tabs();
        }
    }

    public SpecialWriter incrementTabs() {
        tabsLevel++;
        return this;
    }
    public SpecialWriter decrementTabs() {
        tabsLevel--;
        return this;
    }
    public SpecialWriter tabs() {
        String t = "\t".repeat(tabsLevel);
        write(t);
        return this;
    }

    public ListWriterBuilder list() {
        return new ListWriterBuilder(this, "", ListStyle.MULTI_LINE);
    }
    public ListWriterBuilder list(String title) {
        return new ListWriterBuilder(this, title, ListStyle.MULTI_LINE);
    }
    public ListWriterBuilder list(String title, ListStyle listStyle) {
        return new ListWriterBuilder(this, title, listStyle);
    }

    public KeyValueWriter key(String key) {
        return new KeyValueWriter(this, key);
    }
    public KeyValueWriter key(int key) {
        return new KeyValueWriter(this, String.valueOf(key));
    }

    public KeyValueWriter key(Supplier<?> key) {
        return new KeyValueWriter(this, key.get().toString());
    }

    public String get() {
        return stringBuilder.toString();
    }

    @RequiredArgsConstructor
    public static class KeyValueWriter {
        final SpecialWriter parent;
        final String key;
        public SpecialWriter keq() {
            return parent.write(key).write(" = ");
        }
        public SpecialWriter value(String string) {
            return keq().write(string);
        }
        public SpecialWriter value(String string, StringStyle stringStyle) {
            return switch (stringStyle) {
                case DEFAULT -> keq().write(string);
                case QUOTED -> keq().write("\""+string+"\"");
            };
        }
        public SpecialWriter value(Supplier<?> supplier) {
            return keq().write(supplier.get().toString());
        }
        public SpecialWriter value(boolean b) {
            return keq().write(Types.mapBoolean(b));
        }
        public SpecialWriter value(boolean b, BooleanStyle booleanStyle) {
            return switch (booleanStyle) {
                case DEFAULT -> keq().write(Types.mapBoolean(b));
                case UPPER -> keq().write(Types.mapBoolean(b).toUpperCase());
            };
        }
        public SpecialWriter value(Supplier<Boolean> supplier, BooleanStyle booleanStyle) {
            Boolean b = supplier.get();
            return value(b, booleanStyle);
        }
        public SpecialWriter value(Supplier<String> supplier, StringStyle stringStyle) {
            return value(supplier.get(), stringStyle);
        }

        public SpecialWriter value(double d) {
            DoubleStyle doubleStyle = DoubleStyle.defaultStyle();
            String styled = doubleStyle.styled(d);
            return keq().write(styled);
        }
        public SpecialWriter value(double d, DoubleStyle doubleStyle) {
            String styled = doubleStyle.styled(d);
            return keq().write(styled);
        }
        public SpecialWriter value(Supplier<Double> d, DoubleStyle doubleStyle) {
            return value(d.get(), doubleStyle);
        }

        public <T> TestedValue<T> testValue(T valueToTest) {
            return new TestedValue<>(this, valueToTest);
        }
        public <T> TestedValue<T> testValue(Supplier<T> valueToTest) {
            return new TestedValue<>(this, valueToTest.get());
        }

        public SpecialWriter valueBoolean(Supplier<Boolean> bool) {
            return value(bool, BooleanStyle.DEFAULT);
        }

        public SpecialWriter valueDouble(Supplier<Double> doubleSupplier) {
            return value(doubleSupplier.get(), DoubleStyle.defaultStyle());
        }

        public SpecialWriter object(Supplier<Object> supplier, CommonStyles commonStyles) {
            return object(supplier.get(), commonStyles);
        }
        public SpecialWriter object(Object object) {
            return keq().writeObject(object);
        }
        public SpecialWriter object(Object object, CommonStyles commonStyles) {
            return keq().writeObject(object, commonStyles);
        }
        public SpecialWriter object(Supplier<Object> supplier) {
            return object(supplier.get());
        }
    }

    private SpecialWriter writeObject(Object object, Object style) {
        helper.writeWithStyle(this, object, style);
        return this;
    }

    @RequiredArgsConstructor
    public static class TestedValue<T> {
        private final KeyValueWriter parent;
        private final T toTest;

        public SpecialWriter printIf(String eqTo) {
            String str = toTest.toString();
            if (str.equals(eqTo)) {
                return parent.keq().write(str);
            }
            return parent.parent;
        }
        public SpecialWriter printIf(Predicate<T> predicate) {
            if (predicate.test(toTest)) {
                String string = toTest.toString();
                return parent.keq().write(string);
            }
            return parent.parent;
        }
        public SpecialWriter printIf(String eqTo, Function<T, String> mapper) {
            String str = toTest.toString();
            if (str.equals(eqTo)) {
                return parent.keq().write(str);
            }
            return parent.parent;
        }
    }

    @RequiredArgsConstructor
    public static class ListWriterBuilder {
        private final SpecialWriter parent;
        private final String title;
        private final ListStyle listStyle;

        public SpecialWriter block(String blockName, Collection<?> objects) {
            if (hasTitle(title)) {
                parent.write(title).startBlockLn();
            }
            for (Object obj : objects) {
                parent.write(blockName).startBlockLn().writeObject(obj).endBlock();
            }
            if (hasTitle(title)) {
                parent.endBlock();
            }
            return parent;
        }

        public SpecialWriter simple(Collection<?> objects) {

            switch (listStyle) {
                case ONE_LINE -> {
                    if (hasTitle(title)) {
                        parent.write(title).startBlock();
                    }
                    for (Object o : objects) {
                        parent.space().write(o.toString());
                    }
                    if (hasTitle(title)){
                        parent.space().endBlock();
                    }
                }
                case THREE_LINES -> {
                    if (hasTitle(title)) {
                        parent.write(title).startBlockLn();
                    }
                    int i = 0;
                    int target = objects.size() - 1;
                    for (Object o : objects) {
                        parent.write(o.toString());
                        if (i == target) {
                            parent.space();
                        }
                        i++;
                    }
                    if (hasTitle(title)){
                        parent.ln().endBlock();
                    }
                }
                case MULTI_LINE -> {
                    if (hasTitle(title)) {
                        parent.write(title).startBlockLn();
                    }
                    for (Object o : objects) {
                        parent.write(o.toString()).ln();
                    }
                    if (hasTitle(title)){
                        parent.endBlock();
                    }
                }
            }

            if (hasTitle(title)) {
                parent.endBlock();
            }
            return parent;
        }
        public SpecialWriter block(String blockName, Supplier<Collection<?>> supplier) {
            return block(blockName, supplier.get());
        }
    }

    private SpecialWriter startBlock() {
        onBegin();
        stringBuilder.append(" = {");
        incrementTabs();
        return this;
    }

    private SpecialWriter endBlock() {
        decrementTabs();
        onBegin();
        stringBuilder.append("}");
        return this;
    }
    private SpecialWriter endBlockLn() {
        return endBlock().ln();
    }

    private SpecialWriter writeObject(Object obj) {
        helper.write(this, obj);
        return this;
    }

    private SpecialWriter startBlockLn() {
        return startBlock().ln();
    }
    public SpecialWriter ln() {
        stringBuilder.append("\n");
        afterLn = true;
        return this;
    }
    public SpecialWriter space() {
        stringBuilder.append(" ");
        return this;
    }

    public SpecialWriter write(String string) {
        onBegin();
        stringBuilder.append(string);
        return this;
    }


    private static boolean hasTitle(String title) {
        return title != null && !title.isEmpty();
    }
}
