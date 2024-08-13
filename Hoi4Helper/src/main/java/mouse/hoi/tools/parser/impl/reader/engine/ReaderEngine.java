package mouse.hoi.tools.parser.impl.reader.engine;

import jakarta.annotation.PostConstruct;
import mouse.hoi.exception.ReaderException;
import mouse.hoi.tools.parser.impl.ast.*;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Interpreters;
import mouse.hoi.tools.parser.impl.reader.lr.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReaderEngine {
    private final Map<Class<?>, DataReader<?>> readersMap;
    private final Interpreters interpreters;
    public ReaderEngine(List<DataReader<?>> readersList, Interpreters i) {
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
        List<Node> nodes = processRoot(reader, node);
        for (Node n : nodes) {
            switch (n) {
                case KeyValueNode kv -> {
                    LeftValue leftValue = createLeft(kv.getKey());
                    RightValue rightValue = createRight(kv.getValue());
                    reader.onKeyValue(cObj, leftValue, rightValue);
                }
                case SimpleNode sn -> {
                    SimpleValue simpleValue = createSimple(sn);
                    reader.onSimple(cObj, simpleValue);
                }
                case ComplexNode cn -> {
                    ComplexValue complexValue = createComplex(cn);
                    reader.onComplex(cObj, complexValue);
                }
                case null, default -> throw new ReaderException("Unexpected node type: " + node);
            }
        }
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

    private ComplexValue createComplex(ComplexNode complexNode) {
        KeyValueNode keyValueNode = complexNode.getKeyValueNode();
        Node key = keyValueNode.getKey();
        Node value = keyValueNode.getValue();
        BlockNode blockNode = complexNode.getBlockNode();
        LeftValue left = createLeft(key);
        SimpleValue simple = createSimple(value);
        BlockValue blockValue = new BlockValue(blockNode);
        return new ComplexValueImpl(left, simple, blockValue);
    }

    private SimpleValue createSimple(Node node) {
        PossibleValue possibleValue = createPossibleValue(node);
        return (SimpleValue) possibleValue;
    }
    private PossibleValue createPossibleValue(Node node) {
        return switch (node) {
            case DoubleNode d -> new DoubleValue(d.getValue());
            case StringNode s -> new StringValue(s.getValue());
            case IntegerNode i -> new IntegerValue(i.getValue());
            case BooleanNode b -> new BooleanValue(b.isValue());
            case IdNode i -> new StringValue(i.getId());
            case BlockNode b -> new BlockValue(b);
            case null, default -> throw new ReaderException("Unknown node type: " + node);
        };
    }

    private LeftValue createLeft(Node node) {
        PossibleValue possibleValue = createPossibleValue(node);
        return (LeftValue) possibleValue;
    }
    private RightValue createRight(Node node) {
        PossibleValue possibleValue = createPossibleValue(node);
        return (RightValue) possibleValue;
    }


    public <T> T read(AbstractSyntaxTree ast, Class<T> baseClass) {
        return read(ast.root(), baseClass);
    }

    public List<LeftRightValue> getLeftRightValues(BlockNode blockNode) {
        List<Node> nodes = blockNode.getChildren();
        List<LeftRightValue> list = new ArrayList<>();
        for (Node n : nodes) {
            if (n instanceof KeyValueNode kv) {
                LeftValue leftValue = createLeft(kv.getKey());
                RightValue rightValue = createRight(kv.getValue());
                LeftRightValue leftRightValue = new LeftRightValue(leftValue, rightValue);
                list.add(leftRightValue);
            }
        }
        return list;
    }
}
