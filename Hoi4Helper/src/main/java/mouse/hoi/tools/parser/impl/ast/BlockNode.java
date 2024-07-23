package mouse.hoi.tools.parser.impl.ast;

import java.util.ArrayList;
import java.util.List;

public class BlockNode implements Node {
    private final List<Node> includedNodes = new ArrayList<>();
    public void add(Node node) {
        includedNodes.add(node);
    }

    public List<Node> getChildren() {
        return includedNodes;
    }

    @Override
    public String toString() {
        return "BlockNode{" +
                includedNodes +
                '}';
    }
}
