package mouse.hoi.tools.parser.impl.printer.nodes;

import mouse.hoi.tools.parser.impl.printer.PrintableNode;
import mouse.hoi.tools.parser.impl.printer.TreePrintBuilder;

public interface NodePrinter<T extends PrintableNode> {

     Class<T> origin();
     void print(TreePrintBuilder builder, T node);
}
