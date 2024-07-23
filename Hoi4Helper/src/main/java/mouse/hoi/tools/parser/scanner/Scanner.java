package mouse.hoi.tools.parser.scanner;


import mouse.hoi.tools.parser.impl.token.Token;

import java.util.List;

public interface Scanner {
    List<Token> readFile(String filename);
    List<Token> readContent(String content);
}
