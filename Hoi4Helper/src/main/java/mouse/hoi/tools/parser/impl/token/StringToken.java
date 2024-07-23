package mouse.hoi.tools.parser.impl.token;

public record StringToken(Location location, String value) implements Token{
    @Override
    public boolean is(String expected) {
        String testStr = "\"" + expected + "\"";
        return testStr.equals(value);
    }
}
