package mouse.hoi.tools.parser.impl.dom.query;

import mouse.hoi.exception.DomException;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomSimple;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;

import java.util.ArrayList;
import java.util.List;

public class ListedQueryPreprocessor {
    private final DomSimple key;
    private final List<DomData> list;
    private final InterpreterManager interpreterManager;
    public ListedQueryPreprocessor(DomSimple key, List<DomData> list, InterpreterManager interpreterManager) {
        this.key = key;
        this.list = list;
        this.interpreterManager = interpreterManager;
    }

    public <T> ListQueryResult<T> objects(Class<T> clazz) {
        List<T> ts = new ArrayList<>();
        for (DomData d : list) {
            try {
                T object = interpreterManager.createObject(d, clazz);
                ts.add(object);
            } catch (RuntimeException e) {
                throw new DomException("Unable to create listed object with key " + key, e);
            }
        }
        return new ListQueryResult<>(ts);
    }
}
