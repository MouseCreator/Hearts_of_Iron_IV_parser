package mouse.hoi.tools.parser.impl.dom;


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

    private final List<Object> objectList;
    public List<Object> getList() {
       return objectList;
    }
}
