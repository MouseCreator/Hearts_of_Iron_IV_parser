package mouse.hoi.tools.parser.impl.token;


import java.util.List;

public interface TokenCollection {
    void put(Token token);
    List<Token> get();
    TokenStream toTokenStream();
}
