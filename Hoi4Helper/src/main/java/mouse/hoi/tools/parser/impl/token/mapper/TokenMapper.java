package mouse.hoi.tools.parser.impl.token.mapper;

import mouse.hoi.tools.parser.generator.YyResult;
import mouse.hoi.tools.parser.generator.Yytoken;
import mouse.hoi.tools.parser.impl.token.Token;

import java.util.List;

public interface TokenMapper {
    Token mapToToken(String filename, Yytoken yytoken);
    List<Token> mapToToken(YyResult yyResult);
}
