package mouse.hoi.tools.parser.impl.dom.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ListQueryResult<T> {
    private final List<T> values;
    public ListQueryResult(Collection<T> values) {
        this.values = new ArrayList<>(values);
    }
    public void pushAll(Supplier<Collection<T>> collection) {
        Collection<T> ts = collection.get();
        ts.addAll(values);
    }
    public void consumeAll(Consumer<Collection<T>> consumer) {
        consumer.accept(values);
    }

   public List<T> getRaw() {
        return values;
   }
}
