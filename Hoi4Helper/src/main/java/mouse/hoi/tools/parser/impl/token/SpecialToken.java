package mouse.hoi.tools.parser.impl.token;

public record SpecialToken(Location location, String val) implements Token {
    public boolean is(String other) {
        return val.equals(other);
    }
}
