package mouse.hoi.tools.parser.impl.parse;

import lombok.AllArgsConstructor;
import mouse.hoi.exception.ParsingException;
import mouse.hoi.tools.parser.impl.ast.*;
import mouse.hoi.tools.parser.impl.token.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class LeftRecursiveParser implements GameFileParser {
    /*
        id.id -- subscript
        id = id -- key-value
        id = { id } -- block
     */
    @Override
    public AbstractSyntaxTree parse(TokenStream tokenStream) {
        RootNode rootNode = new RootNode();
        while (tokenStream.hasTokens()) {
            Node node = createNode(tokenStream);
            rootNode.add(node);
        }
        return new AbstractSyntaxTreeImpl(rootNode);
    }
    private Node createNode(TokenStream tokenStream) {
        Node left = onLeftValueExpected(tokenStream);
        Node completeNode = onMiddleTermExpected(tokenStream, left);
        if (completeNode == null) {
            throw new ParsingException("Unexpected null node");
        }
        return completeNode;
    }
    private Node onLeftValueExpected(TokenStream tokenStream) {
        Token current = tokenStream.currentToken();
        Optional<Token> lookaheadOptional = tokenStream.lookahead();
        if (lookaheadOptional.isEmpty()) {
            return simpleNodeOf(current);
        }
        Token lookahead = lookaheadOptional.get();
        if (isInfoToken(current)) {
            if (lookahead.is(".")) {
                return createSubscript(tokenStream);
            }
            return simpleNodeOf(current);
        }
        throw unexpected(current);
    }

    private ParsingException unexpected(Token current) {
        throw new ParsingException("Unexpected token: " + current);
    }

    private Node onMiddleTermExpected(TokenStream tokenStream, Node leftCreated) {
        Optional<Token> lookaheadOptional = tokenStream.lookahead();
        if (lookaheadOptional.isEmpty()) {
            return leftCreated;
        }
        Token lookahead = lookaheadOptional.get();
        if (lookahead.is(".")) {
            throw unexpected(lookahead);
        }
        if (lookahead.is("=")) {
            return createKeyValue(tokenStream, leftCreated);
        }
        if (lookahead.is("}")) {
            tokenStream.next();
            return leftCreated;
        }
        if (isInfoToken(lookahead)) {
            tokenStream.next();
            return leftCreated;
        }
        throw unexpected(lookahead);

    }

    private boolean isInfoToken(Token token) {
        return  token instanceof IdToken ||
                token instanceof IntegerToken ||
                token instanceof DoubleToken ||
                token instanceof StringToken || token instanceof DateToken;
    }

    private Node onRightValueExpected(TokenStream tokenStream) {
        Token currentToken = tokenStream.currentToken();
        if (currentToken.is("{")) {
            return createBlock(tokenStream);
        }
        Optional<Token> lookahead = tokenStream.lookahead();
        boolean subs = false;
        if (lookahead.isPresent()) {
            subs = lookahead.get().is(".");
        }
        if (subs) {
            SubscriptNode subscriptNode = new SubscriptNode();
            SimpleNode simpleNode = simpleNodeOf(currentToken);
            subscriptNode.setRoot(simpleNode);
            tokenStream.next();
            Node subscript = createSubscript(tokenStream);
            subscriptNode.setSubscript(subscript);
            return subscriptNode;
        } else {
            SimpleNode simpleNode = simpleNodeOf(currentToken);
            tokenStream.next();
            return simpleNode;
        }
    }

    private BlockNode createBlock(TokenStream tokenStream) {
        tokenStream.nowExpect("{");
        tokenStream.next();
        BlockNode blockNode = new BlockNode();
        while (true) {
            if (tokenStream.currentToken().is("}")) {
                tokenStream.next();
                break;
            }
            Node toAdd = createNode(tokenStream);
            blockNode.add(toAdd);
        }
        return blockNode;
    }

    private Node createKeyValue(TokenStream tokenStream, Node key) {
        KeyValueNode keyValueNode = new KeyValueNode();
        keyValueNode.setKey(key);

        tokenStream.nextExpect("=");
        tokenStream.next();

        Node rightValue = onRightValueExpected(tokenStream);
        keyValueNode.setValue(rightValue);

        Optional<Token> lookahead = tokenStream.lookahead();
        if (lookahead.isEmpty()) {
            return keyValueNode;
        }
        Token tokenLookahead = lookahead.get();
        if (tokenLookahead.is("{")){
            BlockNode block = createBlock(tokenStream);
            return new ComplexNode(keyValueNode, block);
        }
        return keyValueNode;
    }

    private Node createSubscript(TokenStream tokenStream) {
        tokenStream.nowExpect(".");
        tokenStream.next();
        Token current = tokenStream.currentToken();

        SubscriptNode subscriptNode = new SubscriptNode();
        SimpleNode simpleNode = simpleNodeOf(current);
        subscriptNode.setRoot(simpleNode);

        tokenStream.nextExpect(".");
        Token next = tokenStream.next().orElseThrow(()->new ParsingException("Unexpected end of input after '.'"));
        Optional<Token> afterNextOpt = tokenStream.lookahead();
        if (afterNextOpt.isEmpty()) {
            SimpleNode simpleOfNext = simpleNodeOf(next);
            subscriptNode.setSubscript(simpleOfNext);
            return subscriptNode;
        }
        Token afterNext = afterNextOpt.get();
        if (afterNext.is(".")) {
            Node recursiveSubscript = createSubscript(tokenStream);
            subscriptNode.setSubscript(recursiveSubscript);
        } else {
            SimpleNode simpleOfNext = simpleNodeOf(next);
            subscriptNode.setSubscript(simpleOfNext);
        }
        return subscriptNode;
    }

    private SimpleNode simpleNodeOf(Token token) {
        Optional<SimpleNode> optional = simpleNodeOpt(token);
        return optional.orElseThrow(()->new ParsingException("Unable to create a simple token from: " + token));
    }
    private Optional<SimpleNode> simpleNodeOpt(Token token) {
        if (token instanceof StringToken stringToken) {
            return Optional.of(new StringNode(stringToken.location(), stringToken.value()));
        }
        if (token instanceof DateToken dateToken) {
            return Optional.of(new DateNode(dateToken.location(), dateToken.value()));
        }
        if (token instanceof IntegerToken integerToken) {
            return Optional.of(new IntegerNode(integerToken.location(), integerToken.value()));
        }
        if (token instanceof DoubleToken doubleToken) {
            return Optional.of(new DoubleNode(doubleToken.location(), doubleToken.value()));
        }
        if (token instanceof IdToken idToken) {
            return Optional.of(new IdNode(idToken.location(), idToken.id()));
        }
        if (token instanceof BooleanToken b) {
            return Optional.of(new BooleanNode(b.location(), b.value()));
        }
        return Optional.empty();
    }

}
