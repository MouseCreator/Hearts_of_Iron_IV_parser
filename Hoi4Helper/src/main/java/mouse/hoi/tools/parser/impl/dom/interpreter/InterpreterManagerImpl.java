package mouse.hoi.tools.parser.impl.dom.interpreter;

import jakarta.annotation.PostConstruct;
import mouse.hoi.exception.DomException;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.inits.InitsReader;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class InterpreterManagerImpl implements InterpreterManager, InterpreterMapAware {

    private InterpreterMap interpreterMap;

    @Override
    public <T> T createObject(DomData domData, Class<T> clazz) {
        DataReader<?> reader = interpreterMap.getReader(clazz);
        if (reader == null) {
            throw new DomException("No data reader for class: " + clazz);
        }
        Object object = reader.read(domData);
        return clazz.cast(object);
    }

    @Override
    public void fillObject(DomData domData, Object object) {
        Class<?> clazz = object.getClass();
        InitsReader<?> initsReader = interpreterMap.getInits(clazz);
        if (initsReader == null) {
            throw new DomException("No inits reader for class: " + clazz);
        }
        useInterpreter(initsReader, object, domData);
    }

    private <T> void useInterpreter(InitsReader<T> initsReader, Object object, DomData domData) {
        Class<T> type = initsReader.forType();
        T casted = type.cast(object);
        initsReader.read(casted, domData);
    }

    @Override
    public void setInterpreter(InterpreterMap interpreterMap) {
        this.interpreterMap = interpreterMap;
    }
}
