package mouse.hoi.tools.parser.impl.compile;

import lombok.AllArgsConstructor;
import mouse.hoi.tools.parser.impl.ast.AbstractSyntaxTree;
import mouse.hoi.tools.parser.impl.bind.ScanParseBinder;
import mouse.hoi.tools.parser.impl.interpreter.TreeInterpreter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContentCompiler {

    private final ScanParseBinder binder;
    private final TreeInterpreter treeInterpreter;
    public <T> T compile(String content, Class<T> baseClass) {
        AbstractSyntaxTree treeFromContent = binder.createTreeFromContent(content);
        return treeInterpreter.mapToObject(treeFromContent, baseClass);
    }
}
