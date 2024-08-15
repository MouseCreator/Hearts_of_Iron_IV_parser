package mouse.hoi.tools.parser.impl.reader;

import mouse.hoi.exception.ReaderException;
import mouse.hoi.tools.parser.impl.ast.*;
import mouse.hoi.tools.parser.impl.reader.lr.*;
import org.springframework.stereotype.Service;

@Service
public class NodeMapper {

    public ComplexValue createComplex(ComplexNode complexNode) {
        KeyValueNode keyValueNode = complexNode.getKeyValueNode();
        Node key = keyValueNode.getKey();
        Node value = keyValueNode.getValue();
        BlockNode blockNode = complexNode.getBlockNode();
        LeftValue left = createLeft(key);
        SimpleValue simple;
        if (value instanceof SimpleNode sn) {
            simple = createSimple(sn);
        } else {
            throw new IllegalArgumentException("Cannot create complex value: expected simple value, but got " + value);
        }
        BlockValue blockValue = new BlockValue(blockNode);
        return new ComplexValueImpl(left, simple, blockValue);
    }
    public SimpleValue createSimple(SimpleNode node) {
        PossibleValue possibleValue = createPossibleValue(node);
        return (SimpleValue) possibleValue;
    }

    public LeftRightValue createLeftRightValue(KeyValueNode node) {
        Node key = node.getKey();
        LeftValue left = createLeft(key);
        Node value = node.getValue();
        RightValue right = createRight(value);
        return new LeftRightValue(left, right);
    }
    public PossibleValue createPossibleValue(Node node) {
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

    public LeftValue createLeft(Node node) {
        PossibleValue possibleValue = createPossibleValue(node);
        return (LeftValue) possibleValue;
    }
    public RightValue createRight(Node node) {
        PossibleValue possibleValue = createPossibleValue(node);
        return (RightValue) possibleValue;
    }
}
