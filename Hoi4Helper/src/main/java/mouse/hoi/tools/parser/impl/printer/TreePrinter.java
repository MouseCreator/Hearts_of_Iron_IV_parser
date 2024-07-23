package mouse.hoi.tools.parser.impl.printer;

import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.parser.impl.ast.AbstractSyntaxTree;
import mouse.hoi.tools.parser.impl.printer.nodes.PrinterDistributor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TreePrinter {

    private final PrinterDistributor distributor;

    public String printTree(AbstractSyntaxTree tree) {
        TreePrintBuilder builder = new TreePrintBuilder(distributor);
        builder.print(tree.root());
        return builder.get();
    }
}
