package mouse.hoi.tools.parser.impl.reader.helper;

import jakarta.annotation.PostConstruct;
import mouse.hoi.tools.context.Context;
import mouse.hoi.tools.parser.impl.ast.Node;
import mouse.hoi.tools.parser.impl.reader.engine.ReaderEngine;
import org.springframework.stereotype.Service;

@Service
public class InterpretersImpl implements Interpreters {

    private ReaderEngine readerEngine;
    public InterpretersImpl() {
    }

    @Override
    public <T> T read(Class<T> clazz, Node node) {
        return readerEngine.read(node, clazz);
    }
    @Override
    public void setEngine(ReaderEngine engine) {
        this.readerEngine = engine;
    }
}
