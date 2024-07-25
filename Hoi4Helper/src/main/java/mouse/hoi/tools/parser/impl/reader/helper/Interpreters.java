package mouse.hoi.tools.parser.impl.reader.helper;


import mouse.hoi.tools.parser.impl.ast.Node;

public interface Interpreters {
    <T> T read(Class<T> clazz, Node node);
}
