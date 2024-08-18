package mouse.hoi.tools.parser.impl.writer.dom;

import java.util.ArrayList;
import java.util.List;

public class WritingDomImpl implements WritingDom {
    private final List<WritingDomElement> content;

    public WritingDomImpl(List<WritingDomElement> content) {
        this.content = content;
    }

    public void add(WritingDomElement element) {
        content.add(element);
    }
    public List<WritingDomElement> getContent() {
        return new ArrayList<>(content);
    }
}
