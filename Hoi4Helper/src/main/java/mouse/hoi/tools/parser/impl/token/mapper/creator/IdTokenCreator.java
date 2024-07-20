package mouse.hoi.tools.parser.impl.token.mapper.creator;

import mouse.hoi.tools.parser.generator.TokenType;
import mouse.hoi.tools.parser.impl.token.IdToken;
import mouse.hoi.tools.parser.impl.token.Token;
import mouse.hoi.tools.parser.impl.token.mapper.MappingToken;
import org.springframework.stereotype.Service;

@Service
public class IdTokenCreator implements TokenCreator {
    @Override
    public Token createToken(MappingToken mappingToken) {
        return new IdToken(mappingToken.getLocation(), mappingToken.getValue());
    }

    @Override
    public TokenType byType() {
        return TokenType.ID;
    }
}
