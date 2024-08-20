package mouse.hoi.tools.parser.impl.reader.engine;

import mouse.hoi.exception.DomException;
import mouse.hoi.exception.ReaderException;
import mouse.hoi.tools.parser.impl.ast.*;
import mouse.hoi.tools.parser.impl.dom.*;
import mouse.hoi.tools.parser.impl.reader.NodeMapper;
import mouse.hoi.tools.parser.impl.reader.lr.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReaderEngine {
    private final NodeMapper nodeMapper;
    public ReaderEngine(NodeMapper nodeMapper) {
        this.nodeMapper = nodeMapper;
    }


    private List<Node> processRoot(Node node) {
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
    public DomData createDomFromRoot(AbstractSyntaxTree tree) {
        return createDomFromNode(tree.root());
    }
    public DomData createDomFromNode(Node node) {
        return readObject(node);
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
        boolean shouldBeList = first instanceof SimpleNode;
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
        return domList;
    }
    private SimpleValue requiresSimpleKey(Node key) {
        if (key instanceof SimpleNode s) {
            return nodeMapper.createSimple(s);
        }
        throw new DomException("Is not a simple key: " + key);
    }


}
