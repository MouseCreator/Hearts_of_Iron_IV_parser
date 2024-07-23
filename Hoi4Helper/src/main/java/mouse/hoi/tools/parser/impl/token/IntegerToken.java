package mouse.hoi.tools.parser.impl.token;

public record IntegerToken(Location location, int value) implements Token {
    @Override
    public boolean is(String expected) {
        return String.valueOf(value).equals(expected);
    }
}
