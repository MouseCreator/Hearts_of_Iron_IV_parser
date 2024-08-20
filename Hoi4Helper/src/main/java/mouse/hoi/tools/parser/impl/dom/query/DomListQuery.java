package mouse.hoi.tools.parser.impl.dom.query;

import mouse.hoi.tools.parser.impl.dom.DomList;
import mouse.hoi.tools.parser.impl.reader.lr.SimpleValue;

import java.util.ArrayList;
import java.util.List;

public class DomListQuery {

    private final DomList domList;
    public DomListQuery(DomList list) {
        this.domList = list;
    }

    public List<Integer> integerList() {
        List<Integer> result = new ArrayList<>();
        List<SimpleValue> list = domList.getList();
        for (SimpleValue value : list) {
            int i = value.intValue();
            result.add(i);
        }
        return result;
    }

    public List<String> stringList() {
        List<String> result = new ArrayList<>();
        List<SimpleValue> list = domList.getList();
        for (SimpleValue value : list) {
            String  s = value.stringValue();
            result.add(s);
        }
        return result;
    }
}
