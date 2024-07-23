package mouse.hoi.tools.parser.impl.printer;

public interface PrintableNode {

    default void onPrint(TreePrintBuilder treePrintBuilder) {
        treePrintBuilder.print(this);
    }
}
