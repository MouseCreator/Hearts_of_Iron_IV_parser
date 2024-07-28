package mouse.hoi.tools.parser.impl.reader.helper;


import mouse.hoi.tools.parser.impl.ast.Node;
import mouse.hoi.tools.parser.impl.reader.engine.ReaderEngine;

public interface Interpreters {
    <T> T read(Class<T> clazz, Node node);

    void setEngine(ReaderEngine engine);
}
