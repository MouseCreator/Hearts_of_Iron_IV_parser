package mouse.hoi.tools.parser.impl.token;

public interface Token {
    Location location();
    boolean is(String expected);
}