package mouse.hoi.tools.parser.impl.interpreter;

public interface ObjectInitializer {
    <T> T init(Class<T> tClass);
}
