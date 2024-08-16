package mouse.hoi.tools.parser.impl.reader.engine;

import jakarta.annotation.PostConstruct;
import mouse.hoi.exception.DomException;
import mouse.hoi.exception.ReaderException;
import mouse.hoi.tools.parser.impl.ast.*;
import mouse.hoi.tools.parser.impl.dom.*;
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

    private <T> List<Node> processRoot(Node node) {
        List<Node> nodes;
        if (node instanceof BlockNode blockNode) {
            nodes = blockNode.getChildren();
        } else if (node instanceof RootNode rootNode) {
            nodes = rootNode.getChildren();
        } else {
            throw new ReaderException("Unexpected node type! Block or root is expected!");
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
    private DomData readTarget(Node node) {
        if (node instanceof ComplexNode complexNode) {
            return onComplexValue(complexNode);
        }
        if (node instanceof SimpleNode simpleNode) {
            SimpleValue simpleValue = nodeMapper.createSimple(simpleNode);
            return new DomSimple(simpleValue);
        }
        return readObject(node);
    }

    private DomComplex onComplexValue(ComplexNode complexNode) {
        Node value = complexNode.getKeyValueNode().getValue();
        BlockNode blockNode = complexNode.getBlockNode();
        DomData object = readObject(blockNode);
        SimpleValue simpleValue;
        if (value instanceof SimpleNode sn) {
            simpleValue = nodeMapper.createSimple(sn);
        } else {
            throw new DomException("Not a simple value in complex node prefix: " + value);
        }
        DomComplexValue domComplexValue = new DomComplexValue(simpleValue, object);
        return new DomComplex(domComplexValue);
    }

    private DomData readObject(Node node) {
        List<Node> nodes = processRoot(node);
        if (nodes.isEmpty()) {
            return new DomObject();
        }
        Node first = nodes.getFirst();
        boolean shouldBeList = false;
        if (first instanceof SimpleNode) {
            shouldBeList = true;
        }
        if (shouldBeList) {
            return createList(nodes);
        } else {
            return createObject(nodes);
        }
    }

    private DomData createObject(List<Node> nodes) {
        DomObject domObject = new DomObject();
        for (Node node : nodes) {
            if (node instanceof ComplexNode cn) {
                Node key = cn.getKeyValueNode().getKey();
                SimpleValue simpleKey = requiresSimpleKey(key);
                DomSimple domSimple = new DomSimple(simpleKey);
                DomComplex domComplex = onComplexValue(cn);
                domObject.put(domSimple, domComplex);
            }
            else if (node instanceof KeyValueNode kv) {
                Node key = kv.getKey();
                Node value = kv.getValue();
                SimpleValue simpleKey = requiresSimpleKey(key);
                DomSimple domSimple = new DomSimple(simpleKey);
                DomData target = readTarget(value);
                domObject.put(domSimple, target);
            } else {
                throw new DomException("Unexpected node type for object: " + node);
            }
        }
        return domObject;
    }
    private DomData createList(List<Node> nodes) {
        DomList domList = new DomList();
        for (Node node : nodes) {
            if (node instanceof SimpleNode simpleNode) {
                SimpleValue value = nodeMapper.createSimple(simpleNode);
                domList.getList().add(value);
            } else {
                throw new DomException("Unexpected node type for list: " + node);
            }
        }
    }
    private SimpleValue requiresSimpleKey(Node key) {
        if (key instanceof SimpleNode s) {
            return nodeMapper.createSimple(s);
        }
        throw new DomException("Is not a simple key: " + key);
    }


}
