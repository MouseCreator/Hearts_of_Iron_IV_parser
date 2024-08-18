package mouse.hoi.tools.parser.impl.dom.query;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GenericResultQuery<T> {
    private final T value;

    public GenericResultQuery(T value) {
        this.value = value;
    }

    public void set(Consumer<T> consumer) {
        consumer.accept(value);
    }

    public void push(Supplier<Collection<T>> collection) {
        Collection<T> ts = collection.get();
        ts.add(value);
    }
}
