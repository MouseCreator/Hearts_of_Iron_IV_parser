package mouse.hoi.tools.parser.impl.dom.interpreter;

import jakarta.annotation.PostConstruct;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.inits.InitsReader;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InterpreterMap {
    private final Map<Class<?>, DataReader<?>> readerMap;
    private final Map<Class<?>, InitsReader<?>> initsMap;
    private final List<InterpreterMapAware> awares;

    public InterpreterMap(List<DataReader<?>> dataReaderList, List<InitsReader<?>> initsReaders, List<InterpreterMapAware> awares) {
        this.awares = awares;
        readerMap = new HashMap<>();
        initsMap = new HashMap<>();
        for (DataReader<?> reader : dataReaderList) {
            readerMap.put(reader.forType(), reader);
        }
        for (InitsReader<?> inits : initsReaders) {
            initsMap.put(inits.forType(), inits);
        }
    }

    @PostConstruct
    void init() {
        awares.forEach(a->a.setInterpreter(this));
    }

    public InitsReader<?> getInits(Class<?> clazz) {
        return initsMap.get(clazz);
    }
    public DataReader<?> getReader(Class<?> clazz) {
        return readerMap.get(clazz);
    }
}
