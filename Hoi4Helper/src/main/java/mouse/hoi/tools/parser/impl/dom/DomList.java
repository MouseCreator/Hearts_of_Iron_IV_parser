package mouse.hoi.tools.parser.impl.dom;


import mouse.hoi.tools.parser.impl.reader.lr.SimpleValue;

import java.util.ArrayList;
import java.util.List;

public class DomList implements DomData {
    public DomList() {
        this.objectList = new ArrayList<>();
    }

    @Override
    public boolean isList() {
        return true;
    }

    @Override
    public DomList list() {
        return this;
    }

    private final List<SimpleValue> objectList;
    public List<SimpleValue> getList() {
       return objectList;
    }
}
