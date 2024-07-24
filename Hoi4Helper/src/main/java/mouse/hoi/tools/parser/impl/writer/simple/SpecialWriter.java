package mouse.hoi.tools.parser.impl.writer.simple;

import mouse.hoi.tools.parser.impl.printer.TreePrintBuilder;
import mouse.hoi.tools.parser.impl.printer.nodes.PrinterDistributor;

public class SpecialWriter {
    public SpecialWriter() {
        stringBuilder = new StringBuilder();
        tabLevel = 0;
    }

    private final StringBuilder stringBuilder;
    private int tabLevel;
    public SpecialWriter incrementTab() {
        tabLevel++;
        return this;
    }
    public SpecialWriter decrementTab() {
        tabLevel--;
        return this;
    }
    public SpecialWriter resetTab() {
        tabLevel = 0;
        return this;
    }
    public SpecialWriter tln() {
        stringBuilder.append("\n");
        return tabs();
    }
    public SpecialWriter ln() {
        stringBuilder.append("\n");
        return this;
    }
    public SpecialWriter append(String string) {
        stringBuilder.append(string);
        return this;
    }
    public SpecialWriter tabs() {
        if (tabLevel > 0) {
            stringBuilder.append("\t".repeat(tabLevel));
        }
        return this;
    }

    public String get() {
        return stringBuilder.toString();
    }

    public boolean isEmpty() {
        return stringBuilder.isEmpty();
    }
}
