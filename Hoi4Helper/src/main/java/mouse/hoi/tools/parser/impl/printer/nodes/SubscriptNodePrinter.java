package mouse.hoi.tools.parser.impl.printer.nodes;

import mouse.hoi.tools.parser.impl.ast.SubscriptNode;
import mouse.hoi.tools.parser.impl.printer.TreePrintBuilder;
import org.springframework.stereotype.Service;

@Service
public class SubscriptNodePrinter implements NodePrinter<SubscriptNode> {
    @Override
    public Class<SubscriptNode> origin() {
        return SubscriptNode.class;
    }

    @Override
    public void print(TreePrintBuilder builder, SubscriptNode node) {
        builder.print(node.getRoot());
        builder.append(".");
        builder.print(node.getSubscript());
    }
}
