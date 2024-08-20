package mouse.hoi.tools.parser.impl.writer.support;

import mouse.hoi.tools.parser.impl.writer.dw.DWInteger;
import mouse.hoi.tools.parser.impl.writer.dw.DWList;
import mouse.hoi.tools.parser.impl.writer.dw.DWSimple;
import mouse.hoi.tools.parser.impl.writer.style.ListStyle;

import java.util.ArrayList;
import java.util.List;

public class DWListBuilder {
    private final ListStyle listStyle;
    public DWListBuilder(ListStyle style) {
        this.listStyle = style;
    }

    public DWList ofIntegers(List<Integer> list) {
        List<DWSimple> simpleList = new ArrayList<>();
        for (int i : list) {
            DWSimple simple = new DWInteger(i);
            simpleList.add(simple);
        }
        return new DWList(simpleList, listStyle);
    }
}
