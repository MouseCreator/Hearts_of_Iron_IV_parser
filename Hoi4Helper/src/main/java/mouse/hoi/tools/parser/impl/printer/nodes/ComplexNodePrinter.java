package mouse.hoi.tools.parser.impl.printer.nodes;

import mouse.hoi.tools.parser.impl.ast.ComplexNode;
import mouse.hoi.tools.parser.impl.printer.TreePrintBuilder;
import org.springframework.stereotype.Service;

@Service
public class ComplexNodePrinter implements NodePrinter<ComplexNode> {
    @Override
    public Class<ComplexNode> origin() {
        return ComplexNode.class;
    }

    @Override
    public void print(TreePrintBuilder builder, ComplexNode node) {
        builder.print(node.getKeyValueNode());
        builder.append(" ");
        builder.print(node.getBlockNode());
    }
}
