package mouse.hoi.tools.parser.impl.compile;

import lombok.AllArgsConstructor;
import mouse.hoi.tools.parser.impl.ast.AbstractSyntaxTree;
import mouse.hoi.tools.parser.impl.bind.ScanParseBinder;
import mouse.hoi.tools.parser.impl.reader.engine.ReaderEngine;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContentCompiler {

    private final ScanParseBinder binder;
    private final ReaderEngine readerEngine;
    public <T> T compile(String content, Class<T> baseClass) {
        AbstractSyntaxTree ast = binder.createTreeFromContent(content);
        return readerEngine.read(ast, baseClass);
    }
}
