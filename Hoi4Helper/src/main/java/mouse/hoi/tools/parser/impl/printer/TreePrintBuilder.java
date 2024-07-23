package mouse.hoi.tools.parser.impl.printer;


import mouse.hoi.tools.parser.impl.printer.nodes.PrinterDistributor;

public class TreePrintBuilder {

    private final PrinterDistributor distributor;
    public TreePrintBuilder(PrinterDistributor d) {
        stringBuilder = new StringBuilder();
        tabLevel = 0;
        distributor = d;
    }

    private final StringBuilder stringBuilder;
    private int tabLevel;
    public TreePrintBuilder incrementTab() {
        tabLevel++;
        return this;
    }
    public TreePrintBuilder decrementTab() {
        tabLevel--;
        return this;
    }
    public TreePrintBuilder resetTab() {
        tabLevel = 0;
        return this;
    }
    public TreePrintBuilder tln() {
        stringBuilder.append("\n");
        return tabs();
    }
    public TreePrintBuilder ln() {
        stringBuilder.append("\n");
        return this;
    }
    public TreePrintBuilder append(String string) {
        stringBuilder.append(string);
        return this;
    }
    public TreePrintBuilder tabs() {
        if (tabLevel > 0) {
            stringBuilder.append("\t".repeat(tabLevel));
        }
        return this;
    }

    public void print(PrintableNode printable) {
        distributor.onPrint(this, printable);
    }

    public String get() {
        return stringBuilder.toString();
    }
}
