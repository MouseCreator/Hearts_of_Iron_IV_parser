package mouse.hoi.tools.parser.impl.token;

import java.util.ArrayList;
import java.util.List;

public class TokenCollectionImpl implements TokenCollection {
    private final List<Token> tokenList;

    public TokenCollectionImpl() {
        this.tokenList = new ArrayList<>();
    }

    public void put(Token token) {
        tokenList.add(token);
    }

    @Override
    public List<Token> get() {
        return new ArrayList<>(tokenList);
    }


    @Override
    public TokenStream toTokenStream() {
        return null;
    }
}
