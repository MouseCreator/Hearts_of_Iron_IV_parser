package mouse.hoi.tools.parser.impl.dom.interpreter;

import mouse.hoi.exception.DomException;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class InterpreterManagerImpl implements InterpreterManager {

    private final Map<Class<?>,DataReader<?>> map;

    public InterpreterManagerImpl(List<DataReader<?>> dataReaderList) {
        map = new HashMap<>();
        for (DataReader<?> reader : dataReaderList) {
            map.put(reader.forType(), reader);
        }
    }

    @Override
    public <T> T createObject(DomData domData, Class<T> clazz) {
        DataReader<?> reader = map.get(clazz);
        if (reader == null) {
            throw new DomException("No data reader for class: " + clazz);
        }
        Object object = reader.read(domData);
        return clazz.cast(object);
    }
}
