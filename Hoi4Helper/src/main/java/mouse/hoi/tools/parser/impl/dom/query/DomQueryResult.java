package mouse.hoi.tools.parser.impl.dom.query;

import mouse.hoi.exception.DomException;
import mouse.hoi.tools.parser.data.GameDate;
import mouse.hoi.tools.parser.impl.dom.DomData;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class DomQueryResult {
    private final List<DomData> list;
    public DomQueryResult(List<DomData> dataByKey) {
        this.list = dataByKey;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    public void throwIfEmpty() {
        if (isEmpty()) {
            throw new DomException("Expected to get at least one element, but got: " + list);
        }
    }
    public void throwIfEmpty(Supplier<RuntimeException> e) {
        throw e.get();
    }
    public List<DomData> list() {
        return new ArrayList<>(list);
    }
    public Stream<DomData> stream() {
        return list.stream();
    }
    public DomData first() {
        throwIfEmpty();
        return list.getFirst();
    }
    public DomData last() {
        throwIfEmpty();
        return list.getLast();
    }
    public DomData requireSingle() {
        if (list.size() != 1) {
            throw new DomException("Expected to get one element, but got: " + list);
        }
        return first();
    }


    public SimpleResultQuery simple() {
        DomData domData = requireSingle();
        if (domData.isSimple()) {
            return new SimpleResultQuery(domData.simple().val());
        }
        throw new DomException("Expected to be a simple value, but got: " + domData);
    }

    public <T> GenericResultQuery<T> object(Class<T> clazz) {
        DomData domData = requireSingle();
        T mappedObject = createObject(clazz, domData);
        return new GenericResultQuery<>(mappedObject);
    }

    private <T> T createObject(Class<T> clazz, DomData domData) {
        //TODO: add interpretation by class
    }

    public GenericResultQuery<String> string() {
        DomData domData = requeireSimple();
        return new GenericResultQuery<>(domData.simple().val().stringValue());
    }
    public GenericResultQuery<Double> doublev() {
        DomData domData = requeireSimple();
        return new GenericResultQuery<>(domData.simple().val().doubleValue());
    }
    public GenericResultQuery<Boolean> bool() {
        DomData domData = requeireSimple();
        return new GenericResultQuery<>(domData.simple().val().boolValue());
    }
    public GenericResultQuery<Integer> integer() {
        DomData domData = requeireSimple();
        return new GenericResultQuery<>(domData.simple().val().intValue());
    }
    public GenericResultQuery<GameDate> date() {
        DomData domData = requeireSimple();
        return new GenericResultQuery<>(domData.simple().val().dateValue());
    }

    private DomData requeireSimple() {
        DomData domData = requireSingle();
        if (!domData.isSimple()) {
            throw new DomException("Expected to be a simple value, but got: " + domData);
        }
        return domData;
    }
}
