package mouse.hoi.tools.parser.impl.token.mapper;

import mouse.hoi.tools.parser.generator.TokenType;
import mouse.hoi.tools.parser.generator.YyResult;
import mouse.hoi.tools.parser.generator.Yytoken;
import mouse.hoi.exception.ScanException;
import mouse.hoi.tools.parser.impl.token.Location;
import mouse.hoi.tools.parser.impl.token.Token;
import mouse.hoi.tools.parser.impl.token.mapper.creator.TokenCreator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TokenMapperImpl implements TokenMapper {

    private final HashMap<TokenType, TokenCreator> map;

    public TokenMapperImpl(List<TokenCreator> tokenCreatorList) {
        map = new HashMap<>();
        for (TokenCreator creator : tokenCreatorList) {
            map.put(creator.byType(), creator);
        }
    }
    public List<Token> mapToToken(YyResult yyResult) {
        String filename = yyResult.filename();
        List<Token> tokens = new ArrayList<>();
        for (Yytoken yytoken : yyResult.yytokenList()) {
            Token token = mapToToken(filename, yytoken);
            tokens.add(token);
        }
        return tokens;
    }
    public Token mapToToken(String filename, Yytoken yytoken) {
        TokenType type = yytoken.type();
        TokenCreator creator = map.get(type);
        if (creator == null) {
            throw new ScanException("No token creator for type " + type);
        }
        MappingToken mappingToken = createMappingToken(filename, yytoken);
        return creator.createToken(mappingToken);
    }

    private MappingToken createMappingToken(String filename, Yytoken yytoken) {
        MappingToken mappingToken = new MappingToken();
        mappingToken.setValue(yytoken.value());
        Location location = adjustLocation(filename, yytoken);
        mappingToken.setLocation(location);
        return mappingToken;
    }

    private Location adjustLocation(String filename, Yytoken yytoken) {
        int line = yytoken.line();
        int pos = yytoken.column();
        return new Location(filename, line+1, pos+1);
    }


}
