package mouse.hoi.tools.parser.impl.dom.query;

import mouse.hoi.exception.DomException;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.DomKV;
import mouse.hoi.tools.parser.impl.dom.DomObject;
import mouse.hoi.tools.parser.impl.dom.DomSimple;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;

import java.util.List;
import java.util.stream.Stream;

public class DomObjectQuery {
    private final DomObject domObject;
    private final InterpreterManager interpreterManager;

    public DomObjectQuery(DomObject domObject, InterpreterManager interpreterManager) {
        this.domObject = domObject;
        this.interpreterManager = interpreterManager;
    }

    public DomOptionalQueryResult onToken(String string) {
        DomSimple domSimple = new DomSimple(SV.string(string));
        List<DomData> dataByKey = domObject.getIgnoreCase(domSimple);
        return new DomOptionalQueryResult(domSimple, dataByKey, interpreterManager);
    }
    public DomQueryResult requireToken(String string) {
        DomSimple domSimple = new DomSimple(SV.string(string));
        List<DomData> dataByKey = domObject.getIgnoreCase(domSimple);
        return new DomQueryResult(domSimple, dataByKey, interpreterManager);
    }

    public List<DomKV> allTokens() {
        return domObject.orderedKeyValues();
    }

    private List<DomData> getOrThrow(String string, DomSimple domSimple) {
        List<DomData> dataByKey = domObject.getIgnoreCase(domSimple);
        if (dataByKey == null) {
            throw new DomException("Expected to get one value by key '" + string + "' but got none");
        }
        return dataByKey;
    }

    public Stream<DomSimple> tokenStream() {
        return domObject.keys().stream();
    }
}
