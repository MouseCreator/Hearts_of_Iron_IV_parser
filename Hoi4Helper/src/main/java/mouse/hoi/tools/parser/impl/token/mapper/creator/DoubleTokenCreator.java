package mouse.hoi.tools.parser.impl.token.mapper.creator;

import mouse.hoi.tools.parser.generator.TokenType;
import mouse.hoi.exception.ScanException;
import mouse.hoi.tools.parser.impl.token.DoubleToken;
import mouse.hoi.tools.parser.impl.token.Token;
import mouse.hoi.tools.parser.impl.token.mapper.MappingToken;
import org.springframework.stereotype.Service;

@Service
public class DoubleTokenCreator implements TokenCreator{
    @Override
    public Token createToken(MappingToken mappingToken) {
        try {
            double d = Double.parseDouble(mappingToken.getValue());
            return new DoubleToken(mappingToken.getLocation(), d);
        } catch (Exception e) {
            throw new ScanException(e);
        }
    }

    @Override
    public TokenType byType() {
        return TokenType.DOUBLE;
    }
}
