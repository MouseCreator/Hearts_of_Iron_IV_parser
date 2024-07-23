package mouse.hoi.tools.parser.impl.parse;

import mouse.hoi.tools.parser.impl.ast.AbstractSyntaxTree;
import mouse.hoi.tools.parser.impl.token.TokenStream;

public interface GameFileParser {
    AbstractSyntaxTree parse(TokenStream tokenStream);
}
