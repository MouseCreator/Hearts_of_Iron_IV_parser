package mouse.hoi.tools.parser.impl.writer.dom;

import mouse.hoi.tools.parser.impl.writer.SpecialWriter;

import java.util.List;

public class ObjectTarget implements TargetElement{
    private final WritingDom writingDom;
    public ObjectTarget(WritingDom writingDom) {
        this.writingDom = writingDom;
    }
    @Override
    public void write(SpecialWriter specialWriter) {
        specialWriter.write("{").incrementTabs();
        List<WritingDomElement> content = writingDom.getContent();
        for (WritingDomElement element : content) {
            element.write(specialWriter);
        }
        specialWriter.decrementTabs().write("}");
    }
}
