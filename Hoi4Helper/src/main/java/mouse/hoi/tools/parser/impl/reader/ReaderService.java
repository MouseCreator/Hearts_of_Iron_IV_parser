package mouse.hoi.tools.parser.impl.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.parser.impl.ast.AbstractSyntaxTree;
import mouse.hoi.tools.parser.impl.bind.ScanParseBinder;
import mouse.hoi.tools.parser.impl.reader.engine.ReaderEngine;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ScanParseBinder binder;
    private final ReaderEngine engine;
    public <T> T readFromFile(String filename, Class<T> objectClass) {
        AbstractSyntaxTree ast = binder.createTreeFromFile(filename);
        return engine.read(ast, objectClass);
    }
}