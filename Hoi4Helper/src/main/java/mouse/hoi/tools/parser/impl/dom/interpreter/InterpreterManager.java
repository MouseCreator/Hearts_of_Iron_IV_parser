package mouse.hoi.tools.parser.impl.dom.interpreter;

import mouse.hoi.tools.parser.impl.dom.DomData;

public interface InterpreterManager {
    <T> T createObject(DomData domData, Class<T> clazz);
    void fillObject(DomData domData, Object object);
}
