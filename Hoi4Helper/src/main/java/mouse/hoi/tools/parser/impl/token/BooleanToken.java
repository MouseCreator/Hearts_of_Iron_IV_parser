package mouse.hoi.tools.parser.impl.token;

public record BooleanToken(Location location, boolean value) implements Token{
    @Override
    public boolean is(String expected) {
        if (value) {
            return expected.equalsIgnoreCase("yes");
        } else {
            return expected.equalsIgnoreCase("no");
        }
    }
}
