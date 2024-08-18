package mouse.hoi.tools.parser.impl.writer.dom;

import java.util.List;

public interface WritingDom {
    void add(WritingDomElement element);
    List<WritingDomElement> getContent();
}
