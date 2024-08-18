package mouse.hoi.tools.parser.impl.writer.dom;

import mouse.hoi.tools.parser.impl.writer.SpecialWriter;

public class KeyValueElement implements WritingDomElement {
    private SimpleElement keyElement;
    private TargetElement targetElement;
    @Override
    public void write(SpecialWriter specialWriter) {
        keyElement.write(specialWriter);
        specialWriter.write(" = ");
        targetElement.write(specialWriter);
        specialWriter.ln();
    }
}
