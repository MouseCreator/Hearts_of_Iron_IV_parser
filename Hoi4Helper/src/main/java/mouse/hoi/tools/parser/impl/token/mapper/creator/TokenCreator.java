package mouse.hoi.tools.parser.impl.token.mapper.creator;

import mouse.hoi.tools.parser.generator.TokenType;
import mouse.hoi.tools.parser.impl.token.Token;
import mouse.hoi.tools.parser.impl.token.mapper.MappingToken;

public interface TokenCreator {
    Token createToken(MappingToken mappingToken);
    TokenType byType();
}
