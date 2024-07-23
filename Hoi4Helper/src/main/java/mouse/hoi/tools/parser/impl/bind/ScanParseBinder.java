package mouse.hoi.tools.parser.impl.bind;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.parser.impl.ast.AbstractSyntaxTree;
import mouse.hoi.tools.parser.impl.parse.GameFileParser;
import mouse.hoi.tools.parser.impl.token.Token;
import mouse.hoi.tools.parser.impl.token.TokenStream;
import mouse.hoi.tools.parser.impl.token.TokenStreamImpl;
import mouse.hoi.tools.parser.scanner.Scanner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScanParseBinder {
    private final Scanner scanner;
    private final GameFileParser gameFileParser;
    public AbstractSyntaxTree createTreeFromContent(String content) {
        List<Token> tokens = scanner.readContent(content);
        TokenStream tokenStream = new TokenStreamImpl(tokens);
        return gameFileParser.parse(tokenStream);
    }
    public AbstractSyntaxTree createTreeFromFile(String filename) {
        List<Token> tokens = scanner.readFile(filename);
        TokenStream tokenStream = new TokenStreamImpl(tokens);
        return gameFileParser.parse(tokenStream);
    }
}
