package mouse.hoi.tools.parser.impl.token;

public class Tokens {
    public static SpecialToken token(Location location, String s) {
        return new SpecialToken(location, s);
    }
    public static IdToken id(Location location, String s) {
        return new IdToken(location, s);
    }
}
