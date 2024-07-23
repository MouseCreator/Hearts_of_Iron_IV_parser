package mouse.hoi.tools.parser.impl.token;

public record IdToken(Location location, String id) implements Token{
    @Override
    public boolean is(String expected) {
        return expected.equals(id);
    }
}
