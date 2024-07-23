package mouse.hoi.tools.parser.impl.ast;

import java.util.ArrayList;
import java.util.List;

public class RootNode implements Node {
    private final List<Node> children = new ArrayList<>();
    public void add(Node node) {
        children.add(node);
    }

    public List<Node> getChildren() {
        return new ArrayList<>(children);
    }
}
