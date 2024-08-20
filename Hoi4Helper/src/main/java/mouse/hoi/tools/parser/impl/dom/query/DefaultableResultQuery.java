package mouse.hoi.tools.parser.impl.dom.query;

import mouse.hoi.exception.DomException;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DefaultableResultQuery<T> {
    private final T value;
    private final boolean empty;
    private DefaultableResultQuery(T value, boolean empty) {
        this.value = value;
        this.empty = empty;
    }
    public static <R> DefaultableResultQuery<R> empty() {
        return new DefaultableResultQuery<>(null, true);
    }
    public static <R> DefaultableResultQuery<R> with(R value) {
        return new DefaultableResultQuery<>(value, false);
    }
    public void setOrSupply(Consumer<T> consumer, Supplier<T> t) {
        if (empty) {
            consumer.accept(t.get());
        } else {
            consumer.accept(value);
        }
    }

    public void setOrNull(Consumer<T> consumer) {
        if (empty) {
            consumer.accept(null);
        } else {
            consumer.accept(value);
        }
    }
    public void setOrDefault(Consumer<T> consumer, T t) {
        if (empty) {
            consumer.accept(t);
        } else {
            consumer.accept(value);
        }
    }
    public void push(Supplier<Collection<T>> collection) {
        Collection<T> ts = collection.get();
        if (!empty) {
            ts.add(value);
        }
    }

    public void setOrSkip(Consumer<T> consumer) {
        if (empty) {
            return;
        }
        consumer.accept(value);
    }

    public void setOrThrow(Consumer<T> consumer) {
        if (empty) {
            throw new DomException("Unexpected empty value!");
        }
        consumer.accept(value);
    }
}
