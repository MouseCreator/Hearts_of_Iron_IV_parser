package mouse.hoi.tools.parser.impl.printer.nodes;

import mouse.hoi.tools.parser.impl.ast.ComparisonNode;
import mouse.hoi.tools.parser.impl.printer.TreePrintBuilder;

public class ComparisonNodePrinter implements NodePrinter<ComparisonNode> {
    @Override
    public Class<ComparisonNode> origin() {
        return ComparisonNode.class;
    }

    @Override
    public void print(TreePrintBuilder builder, ComparisonNode node) {
        builder.print(node.getKey());
        builder.append(" " + node.getSign() + " ");
        builder.print(node.getValue());
    }
}
