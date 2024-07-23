package mouse.hoi.tools.parser.impl.printer.nodes;

import mouse.hoi.exception.PrinterException;
import mouse.hoi.tools.parser.impl.ast.Node;
import mouse.hoi.tools.parser.impl.printer.PrintableNode;
import mouse.hoi.tools.parser.impl.printer.TreePrintBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrinterDistributor {
    public final List<NodePrinter<? extends Node>> printers;

    public PrinterDistributor(List<NodePrinter<? extends Node>> printers) {
        this.printers = printers;
    }


    public void onPrint(TreePrintBuilder builder, PrintableNode printableNode) {
        boolean success = false;
        for (NodePrinter<? extends  Node> printer : printers) {
            success = usePrinter(builder, printer, printableNode);
            if (success) {
                break;
            }
        }
        if (!success) {
            throw new PrinterException("Unable to print node: " + printableNode);
        }
    }

    private <T extends Node> boolean  usePrinter(TreePrintBuilder builder, NodePrinter<T> printer, PrintableNode printableNode) {
        Class<T> origin = printer.origin();
        if (origin.isAssignableFrom(printableNode.getClass())) {
            printer.print(builder, origin.cast(printableNode));
            return true;
        }
        return false;
    }
}
