package mouse.hoi.tools.parser.impl.printer.nodes;

import mouse.hoi.tools.parser.impl.ast.BlockNode;
import mouse.hoi.tools.parser.impl.ast.Node;
import mouse.hoi.tools.parser.impl.printer.TreePrintBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlockNodePrinter implements NodePrinter<BlockNode> {
    @Override
    public Class<BlockNode> origin() {
        return BlockNode.class;
    }

    @Override
    public void print(TreePrintBuilder builder, BlockNode node) {
        builder.append("{").incrementTab();
        List<Node> children = node.getChildren();
        for (Node child : children) {
            builder.tln();
            builder.print(child);
        }
        builder.decrementTab().tln().append("}");
    }

}
