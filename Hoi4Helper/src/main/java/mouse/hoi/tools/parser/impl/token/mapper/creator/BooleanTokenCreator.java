package mouse.hoi.tools.parser.impl.token.mapper.creator;

import mouse.hoi.tools.parser.generator.TokenType;
import mouse.hoi.tools.parser.impl.token.BooleanToken;
import mouse.hoi.tools.parser.impl.token.Token;
import mouse.hoi.tools.parser.impl.token.mapper.MappingToken;
import mouse.hoi.tools.utils.Types;
import org.springframework.stereotype.Service;

@Service
public class BooleanTokenCreator implements TokenCreator {
    @Override
    public Token createToken(MappingToken mappingToken) {
        String value = mappingToken.getValue();
        boolean b = Types.mapBoolean(value);
        return new BooleanToken(mappingToken.getLocation(), b);
    }

    @Override
    public TokenType byType() {
        return TokenType.BOOLEAN;
    }
}
