package mouse.hoi.tools.parser.impl.token;


import java.util.Optional;

public interface TokenStream {
    Token currentToken();
    Optional<Token> lookahead();
    Optional<Token> next();
    Token nextExpect(String s);
    Token nowExpect(String s);
    boolean hasTokens();
}
