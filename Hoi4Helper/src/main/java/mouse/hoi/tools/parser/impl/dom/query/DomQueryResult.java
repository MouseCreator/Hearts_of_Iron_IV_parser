package mouse.hoi.tools.parser.impl.dom.query;

import mouse.hoi.exception.DomException;
import mouse.hoi.tools.parser.data.GameDate;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomList;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.reader.lr.PossibleValue;
import mouse.hoi.tools.parser.impl.reader.lr.SimpleValue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class DomQueryResult {
    private final List<DomData> list;

    private final InterpreterManager interpreterManager;
    public DomQueryResult(List<DomData> dataByKey, InterpreterManager interpreterManager) {
        this.list = dataByKey;
        this.interpreterManager = interpreterManager;
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
    public <T> GenericResultQuery<T> object(Supplier<T> initializedObject) {
        DomData domData = requireSingle();
        T t = initializedObject.get();
        fillObject(t, domData);
        return new GenericResultQuery<>(t);
    }

    private void fillObject(Object object, DomData domData) {
        interpreterManager.fillObject(domData, object);
    }

    private <T> T createObject(Class<T> clazz, DomData domData) {
        return interpreterManager.createObject(domData, clazz);
    }

    public GenericResultQuery<String> string() {
        DomData domData = requireSimple();
        return new GenericResultQuery<>(domData.simple().val().stringValue());
    }
    public GenericResultQuery<Double> doublev() {
        DomData domData = requireSimple();
        return new GenericResultQuery<>(domData.simple().val().doubleValue());
    }
    public GenericResultQuery<Boolean> bool() {
        DomData domData = requireSimple();
        return new GenericResultQuery<>(domData.simple().val().boolValue());
    }
    public GenericResultQuery<Integer> integer() {
        DomData domData = requireSimple();
        return new GenericResultQuery<>(domData.simple().val().intValue());
    }
    public GenericResultQuery<GameDate> date() {
        DomData domData = requireSimple();
        return new GenericResultQuery<>(domData.simple().val().dateValue());
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

    public GenericResultQuery<List<Integer>> integerList() {
        DomList domData = requireList();
        List<Integer> integers = domData.getList().stream().map(PossibleValue::intValue).toList();
        return new GenericResultQuery<>(integers);
    }

    public GenericResultQuery<List<String>> stringList() {
        DomList domData = requireList();
        List<String> strings = domData.getList().stream().map(PossibleValue::stringValue).toList();
        return new GenericResultQuery<>(strings);
    }
}
