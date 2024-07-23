package mouse.hoi.tools.parser.impl.printer.nodes;

import mouse.hoi.tools.parser.impl.ast.KeyValueNode;
import mouse.hoi.tools.parser.impl.printer.TreePrintBuilder;
import org.springframework.stereotype.Service;

@Service
public class KeyValueNodePrinter implements NodePrinter<KeyValueNode> {
    @Override
    public Class<KeyValueNode> origin() {
        return KeyValueNode.class;
    }

    @Override
    public void print(TreePrintBuilder builder, KeyValueNode node) {
        builder.print(node.getKey());
        builder.append( " = ");
        builder.print(node.getValue());
    }
}
