package mouse.hoi.tools.parser.impl.writer.dom;

import java.util.List;

public class TargetList {

    private final List<SimpleElement> elements;

    public TargetList(List<SimpleElement> elements) {
        this.elements = elements;
    }

    public List<SimpleElement> elements() {
        return elements;
    }
}
