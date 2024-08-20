package mouse.hoi.tools.parser.impl.dom.query;

import mouse.hoi.exception.DomException;
import mouse.hoi.tools.parser.data.GameDate;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomList;
import mouse.hoi.tools.parser.impl.dom.DomSimple;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.reader.lr.PossibleValue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class DomOptionalQueryResult {
    private final DomSimple key;
    private final List<DomData> list;
    private final InterpreterManager interpreterManager;
    public DomOptionalQueryResult(DomSimple key, List<DomData> dataByKey, InterpreterManager interpreterManager) {
        this.key = key;
        this.list = dataByKey;
        this.interpreterManager = interpreterManager;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    public void throwIfEmpty() {
        if (isEmpty()) {
            throw new DomException("Expected to get at least one element, but got: " + list + ". Key: " + key);
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
            throw new DomException("Expected to get one element, but got: " + list + ". Key: " + key);
        }
        return first();
    }
    public <T> DefaultableResultQuery<T> object(Class<T> clazz) {
        if (isEmpty()) {
            return DefaultableResultQuery.empty();
        }
        DomData domData = requireSingle();
        T mappedObject = createObject(clazz, domData);
        return DefaultableResultQuery.with(mappedObject);
    }
    public <T> DefaultableResultQuery<T> object(Supplier<T> initializedObject) {
        if (isEmpty()) {
            return DefaultableResultQuery.empty();
        }
        DomData domData = requireSingle();
        T t = initializedObject.get();
        fillObject(t, domData);
        return DefaultableResultQuery.with(t);
    }

    private void fillObject(Object object, DomData domData) {
        interpreterManager.fillObject(domData, object);
    }

    private <T> T createObject(Class<T> clazz, DomData domData) {
        return interpreterManager.createObject(domData, clazz);
    }

    public DefaultableResultQuery<String> string() {
        if (isEmpty()) {
            return DefaultableResultQuery.empty();
        }
        DomData domData = requireSimple();
        return DefaultableResultQuery.with(domData.simple().val().stringValue());
    }
    public DefaultableResultQuery<Double> doublev() {
        if (isEmpty()) {
            return DefaultableResultQuery.empty();
        }
        DomData domData = requireSimple();
        return DefaultableResultQuery.with(domData.simple().val().doubleValue());
    }
    public DefaultableResultQuery<Boolean> bool() {
        if (isEmpty()) {
            return DefaultableResultQuery.empty();
        }
        DomData domData = requireSimple();
        return DefaultableResultQuery.with(domData.simple().val().boolValue());
    }
    public DefaultableResultQuery<Integer> integer() {
        if (isEmpty()) {
            return DefaultableResultQuery.empty();
        }
        DomData domData = requireSimple();
        return DefaultableResultQuery.with(domData.simple().val().intValue());
    }
    public DefaultableResultQuery<GameDate> date() {
        if (isEmpty()) {
            return DefaultableResultQuery.empty();
        }
        DomData domData = requireSimple();
        return DefaultableResultQuery.with(domData.simple().val().dateValue());
    }

    private DomData requireSimple() {
        DomData domData = requireSingle();
        if (!domData.isSimple()) {
            throw new DomException("Expected to be a simple value, but got: " + domData);
        }
        return domData;
    }

    private DomList requireList() {
        DomData domData = requireSingle();
        if (!domData.isList()) {
            throw new DomException("Expected to be a list value, but got: " + domData);
        }
        return domData.list();
    }
    public DefaultableResultQuery<List<Integer>> integerList() {
        if (isEmpty()) {
            return DefaultableResultQuery.empty();
        }
        DomList domData = requireList();
        List<Integer> integers = domData.getList().stream().map(PossibleValue::intValue).toList();
        return DefaultableResultQuery.with(integers);
    }

    public DefaultableResultQuery<List<String>> stringList() {
        if (isEmpty()) {
            return DefaultableResultQuery.empty();
        }
        DomList domData = requireList();
        List<String> strings = domData.getList().stream().map(PossibleValue::stringValue).toList();
        return DefaultableResultQuery.with(strings);
    }

    public <T> DefaultableResultQuery<T> objectRaw(T object) {
        if (isEmpty()) {
            return DefaultableResultQuery.empty();
        }
        DomData d = requireSingle();
        fillObject(object, d);
        return DefaultableResultQuery.with(object);
    }
}
