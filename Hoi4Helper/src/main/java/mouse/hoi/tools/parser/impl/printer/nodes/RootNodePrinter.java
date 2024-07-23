package mouse.hoi.tools.parser.impl.printer.nodes;

import mouse.hoi.tools.parser.impl.ast.Node;
import mouse.hoi.tools.parser.impl.ast.RootNode;
import mouse.hoi.tools.parser.impl.printer.TreePrintBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RootNodePrinter implements NodePrinter<RootNode> {

    @Override
    public Class<RootNode> origin() {
        return RootNode.class;
    }

    @Override
    public void print(TreePrintBuilder builder, RootNode node) {
        List<Node> children = node.getChildren();
        for (Node child : children) {
            builder.print(child);
            builder.tln();
        }
    }
}
