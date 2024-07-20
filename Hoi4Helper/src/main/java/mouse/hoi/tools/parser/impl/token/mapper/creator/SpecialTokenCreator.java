package mouse.hoi.tools.parser.impl.token.mapper.creator;

import mouse.hoi.tools.parser.generator.TokenType;
import mouse.hoi.tools.parser.impl.token.SpecialToken;
import mouse.hoi.tools.parser.impl.token.Token;
import mouse.hoi.tools.parser.impl.token.mapper.MappingToken;
import org.springframework.stereotype.Service;

@Service
public class SpecialTokenCreator implements TokenCreator {
    @Override
    public Token createToken(MappingToken mappingToken) {
        return new SpecialToken(mappingToken.getLocation(), mappingToken.getValue());
    }

    @Override
    public TokenType byType() {
        return TokenType.SPECIAL;
    }
}
