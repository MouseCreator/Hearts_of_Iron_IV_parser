package mouse.hoi.tools.parser.scanner;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.parser.generator.YyCall;
import mouse.hoi.tools.parser.generator.YyResult;
import mouse.hoi.tools.parser.impl.token.Token;
import mouse.hoi.tools.parser.impl.token.mapper.TokenMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ScannerImpl implements Scanner {
    private final TokenMapper tokenMapper;
    private final YyCall yyCall;
    @Override
    public List<Token> readFile(String filename) {
        YyResult call = yyCall.call(filename);
        return tokenMapper.mapToToken(call);
    }

    @Override
    public List<Token> readContent(String content) {
        YyResult call = yyCall.read(content);
        return tokenMapper.mapToToken(call);
    }
}
