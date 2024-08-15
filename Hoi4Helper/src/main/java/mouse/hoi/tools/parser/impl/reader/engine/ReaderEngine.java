package mouse.hoi.tools.parser.impl.reader.engine;

import jakarta.annotation.PostConstruct;
import mouse.hoi.exception.ReaderException;
import mouse.hoi.tools.parser.impl.ast.*;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.NodeMapper;
import mouse.hoi.tools.parser.impl.reader.helper.Interpreters;
import mouse.hoi.tools.parser.impl.reader.lr.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReaderEngine {
    private final Map<Class<?>, DataReader<?>> readersMap;
    private final Interpreters interpreters;
    private final NodeMapper nodeMapper;
    public ReaderEngine(List<DataReader<?>> readersList, Interpreters i, NodeMapper nodeMapper) {
        this.nodeMapper = nodeMapper;
        this.readersMap = new HashMap<>();
        for (DataReader<?> reader : readersList) {
            readersMap.put(reader.forType(), reader);
        }
        this.interpreters = i;
    }

    @PostConstruct
    void init() {
        interpreters.setEngine(this);
    }


    public <T> T read(Node node, Class<T> classToRead) {
        DataReader<?> reader = readersMap.get(classToRead);
        if (reader == null) {
            throw new ReaderException("No reader for class: " + classToRead);
        }
        return classToRead.cast(initializeAndApply(reader, node));
    }

    private <T> T initializeAndApply(DataReader<T> reader, Node node) {
        Object obj = reader.initialize();
        T cObj = reader.forType().cast(obj);
        readObject(reader, node, cObj);
        return cObj;
    }

    private <T> List<Node> processRoot(DataReader<T> reader, Node node) {
        List<Node> nodes;
        if (node instanceof BlockNode blockNode) {
            nodes = blockNode.getChildren();
        } else if (node instanceof RootNode rootNode) {
            nodes = rootNode.getChildren();
        } else {
            throw new ReaderException("Unexpected node type! Reader for " + reader.forType() + " encountered " + node);
        }
        return nodes;
    }

    public <T> T read(AbstractSyntaxTree ast, Class<T> baseClass) {
        return read(ast.root(), baseClass);
    }

    public List<LeftRightValue> getLeftRightValues(BlockNode blockNode) {
        List<Node> nodes = blockNode.getChildren();
        List<LeftRightValue> list = new ArrayList<>();
        for (Node n : nodes) {
            if (n instanceof KeyValueNode kv) {
                LeftValue leftValue = nodeMapper.createLeft(kv.getKey());
                RightValue rightValue = nodeMapper.createRight(kv.getValue());
                LeftRightValue leftRightValue = new LeftRightValue(leftValue, rightValue);
                list.add(leftRightValue);
            }
        }
        return list;
    }

    public <T> void readObject(Node node, T obj) {
        Class<?> aClass = obj.getClass();
        DataReader<?> reader = readersMap.get(aClass);
        if (reader == null) {
            throw new ReaderException("No reader for type: " + aClass);
        }
        t_readObject(reader, node, obj);
    }

    private <T> void t_readObject(DataReader<T> reader, Node node, Object obj) {
        Class<T> clazz = reader.forType();
        T cast = clazz.cast(obj);
        readObject(reader, node, cast);
    }

    private <T> void readObject(DataReader<T> reader, Node node, T cObj) {
        List<Node> nodes = processRoot(reader, node);
        for (Node n : nodes) {
            switch (n) {
                case KeyValueNode kv -> {
                    LeftValue leftValue = nodeMapper.createLeft(kv.getKey());
                    RightValue rightValue = nodeMapper.createRight(kv.getValue());
                    reader.onKeyValue(cObj, leftValue, rightValue);
                }
                case SimpleNode sn -> {
                    SimpleValue simpleValue = nodeMapper.createSimple(sn);
                    reader.onSimple(cObj, simpleValue);
                }
                case ComplexNode cn -> {
                    ComplexValue complexValue = nodeMapper.createComplex(cn);
                    reader.onComplex(cObj, complexValue);
                }
                case null, default -> throw new ReaderException("Unexpected node type: " + node);
            }
        }
    }
}
