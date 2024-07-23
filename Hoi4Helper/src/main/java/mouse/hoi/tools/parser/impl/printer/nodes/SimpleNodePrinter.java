package mouse.hoi.tools.parser.impl.printer.nodes;

import mouse.hoi.tools.parser.impl.ast.SimpleNode;
import mouse.hoi.tools.parser.impl.printer.TreePrintBuilder;
import org.springframework.stereotype.Service;

@Service
public class SimpleNodePrinter implements NodePrinter<SimpleNode> {
    @Override
    public Class<SimpleNode> origin() {
        return SimpleNode.class;
    }

    @Override
    public void print(TreePrintBuilder builder, SimpleNode node) {
        builder.append(node.print());
    }
}
