package mouse.hoi.tools.parser.impl.token;

public record DoubleToken(Location location, double value) implements Token {

    @Override
    public boolean is(String expected) {
        return String.valueOf(value).equals(expected);
    }
}
