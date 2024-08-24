package mouse.hoi.tools.parser.impl.dom.query;

import mouse.hoi.exception.DomException;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomSimple;

import java.util.List;
import java.util.function.Function;

public class DomMappedProcessor {
    private final DomSimple key;
    private final List<DomData> list;
    public DomMappedProcessor(DomSimple key, List<DomData> list) {
        this.key = key;
        this.list = list;
    }
    private boolean isEmpty() {
        return list.isEmpty();
    }
    public <S> DefaultableResultQuery<S> doublev(Function<Double, S> function) {
        if (isEmpty()) {
            return DefaultableResultQuery.empty();
        }
        DomData domData = requireSingle();
        double d = domData.simple().val().doubleValue();
        S s = function.apply(d);
        return DefaultableResultQuery.with(s);
    }

    private DomData requireSingle() {
        if (list.size() != 1) {
            throw new DomException("Expected to get one element, but got: " + list + ". Key: " + key);
        }
        return list.getFirst();
    }
}
