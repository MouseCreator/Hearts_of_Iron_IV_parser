package mouse.hoi.tools.parser.impl.token.mapper.creator;

import mouse.hoi.tools.parser.generator.TokenType;
import mouse.hoi.exception.ScanException;
import mouse.hoi.tools.parser.impl.token.IntegerToken;
import mouse.hoi.tools.parser.impl.token.Token;
import mouse.hoi.tools.parser.impl.token.mapper.MappingToken;
import org.springframework.stereotype.Service;

@Service
public class IntTokenCreator implements TokenCreator {
    @Override
    public Token createToken(MappingToken mappingToken) {
        try {
            int val = Integer.parseInt(mappingToken.getValue());
            return new IntegerToken(mappingToken.getLocation(), val);
        } catch (Exception e) {
            throw new ScanException(e);
        }
    }

    @Override
    public TokenType byType() {
        return TokenType.INT;
    }
}
