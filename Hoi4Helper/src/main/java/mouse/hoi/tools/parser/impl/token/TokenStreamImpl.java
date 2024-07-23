package mouse.hoi.tools.parser.impl.token;

import mouse.hoi.exception.ParsingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TokenStreamImpl implements TokenStream {

    private final List<Token> tokenList;

    private int pointer;

    public TokenStreamImpl(List<Token> tokenList) {
        this.tokenList = new ArrayList<>(tokenList);
        pointer = 0;
    }

    @Override
    public Token currentToken() {
        if (isOutOfRange()) {
            throw new ParsingException("Cannot get current token: Out of tokens!");
        }
        return tokenList.get(pointer);
    }

    private boolean isOutOfRange() {
        return pointer >= tokenList.size();
    }

    @Override
    public Optional<Token> lookahead() {
        int lookahead = pointer + 1;
        if (lookahead >= tokenList.size()) {
            return Optional.empty();
        }
        return Optional.of(tokenList.get(lookahead));
    }

    @Override
    public Optional<Token> next() {
        pointer++;
        if (isOutOfRange()) {
            return Optional.empty();
        }
        return Optional.of(currentToken());
    }

    @Override
    public Token nextExpect(String s) {
        Optional<Token> nextOpt = next();
        Token next = nextOpt.orElseThrow(()-> new ParsingException("Unexpected end of input. Expected: " + s));
        if (next.is(s)) {
            return next;
        }
        throw new ParsingException("Expected '" + s + "' , but got " + next);
    }

    @Override
    public Token nowExpect(String s) {
        Token current = currentToken();
        if (current.is(s)) {
            return current;
        }
        throw new ParsingException("Expected '" + s + "', but got " + current);
    }

    @Override
    public boolean hasTokens() {
        return pointer < tokenList.size();
    }
}
