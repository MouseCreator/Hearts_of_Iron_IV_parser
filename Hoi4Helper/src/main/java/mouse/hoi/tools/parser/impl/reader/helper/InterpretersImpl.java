package mouse.hoi.tools.parser.impl.reader.helper;

import jakarta.annotation.PostConstruct;
import mouse.hoi.tools.context.Context;
import mouse.hoi.tools.parser.impl.ast.BlockNode;
import mouse.hoi.tools.parser.impl.ast.Node;
import mouse.hoi.tools.parser.impl.reader.engine.ReaderEngine;
import mouse.hoi.tools.parser.impl.reader.lr.LeftRightValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterpretersImpl implements Interpreters {

    private ReaderEngine readerEngine;
    public InterpretersImpl() {
    }

    @Override
    public <T> void readObj(T obj, Node node) {
        readerEngine.readObject(node, obj);
    }

    @Override
    public <T> T read(Class<T> clazz, Node node) {
        return readerEngine.read(node, clazz);
    }
    @Override
    public void setEngine(ReaderEngine engine) {
        this.readerEngine = engine;
    }

    @Override
    public List<LeftRightValue> getLeftRightValues(BlockNode blockNode) {
        return readerEngine.getLeftRightValues(blockNode);
    }
}
