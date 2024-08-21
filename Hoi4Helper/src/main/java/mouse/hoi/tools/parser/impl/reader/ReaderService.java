package mouse.hoi.tools.parser.impl.reader;

import lombok.RequiredArgsConstructor;
import mouse.hoi.exception.ParsingException;
import mouse.hoi.tools.parser.impl.ast.AbstractSyntaxTree;
import mouse.hoi.tools.parser.impl.bind.ScanParseBinder;
import mouse.hoi.tools.parser.impl.dom.DomData;
import mouse.hoi.tools.parser.impl.dom.interpreter.InterpreterManager;
import mouse.hoi.tools.parser.impl.reader.engine.ReaderEngine;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ScanParseBinder binder;
    private final ReaderEngine engine;
    private final InterpreterManager interpreterManager;
    public <T> T readFromFile(String filename, Class<T> objectClass) {
        try {
            AbstractSyntaxTree ast = binder.createTreeFromFile(filename);
            DomData domFromRoot = engine.createDomFromRoot(ast);
            return interpreterManager.createObject(domFromRoot, objectClass);
        } catch (ParsingException e) {
            throw new ParsingException("Error reading " + filename, e);
        }

    }
}
