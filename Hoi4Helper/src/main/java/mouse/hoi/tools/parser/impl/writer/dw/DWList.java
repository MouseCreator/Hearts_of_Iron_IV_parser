package mouse.hoi.tools.parser.impl.writer.dw;

import mouse.hoi.tools.parser.impl.writer.style.ListStyle;
import mouse.hoi.tools.parser.impl.writer.SpecialWriter;

import java.util.List;

public class DWList implements DWData {
    private final List<DWSimple> simpleList;
    private final ListStyle listStyle;
    public DWList(List<DWSimple> simpleList) {
        this.simpleList = simpleList;
        this.listStyle = ListStyle.MULTI_LINE;
    }

    public DWList(List<DWSimple> simpleList, ListStyle listStyle) {
        this.simpleList = simpleList;
        this.listStyle = listStyle;
    }

    @Override
    public void write(SpecialWriter writer) {
        switch (listStyle) {
            case ONE_LINE -> {
                writer.incrementTabs().write("{ ");
                for (DWSimple simple : simpleList) {
                    writer.write(simple).space();
                }
                writer.decrementTabs().write("}");
            }
            case THREE_LINES -> {
                writer.incrementTabs().write("{").ln();
                for (int i = 0; i < simpleList.size(); i++) {
                    DWSimple simple = simpleList.get(i);
                    writer.write(simple);
                    if (i != simpleList.size() - 1) {
                        writer.space();
                    }
                }
                writer.decrementTabs().ln().write("}");
            }
            case MULTI_LINE -> {
                writer.incrementTabs().write("{").ln();
                for (DWSimple simple : simpleList) {
                    writer.write(simple).ln();
                }
                writer.decrementTabs().write("}");
            }
        }
    }
}
