package mouse.hoi.tools.parser.impl.compile;

public interface GameCompiler {
    <T> T compile(String filename, Class<T> baseClass);
}
