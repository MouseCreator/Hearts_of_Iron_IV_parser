package mouse.hoi.tools.parser.impl.dom;


import lombok.EqualsAndHashCode;
import mouse.hoi.tools.parser.impl.reader.lr.SimpleValue;

@EqualsAndHashCode
public class DomSimple implements DomData {

    private final SimpleValue simpleValue;

    public DomSimple(SimpleValue simpleValue) {
        this.simpleValue = simpleValue;
    }

    @Override
    public boolean isSimple() {
        return true;
    }

    @Override
    public DomSimple simple() {
        return this;
    }

    public SimpleValue val() {
        return simpleValue;
    }

    @Override
    public String toString() {
        return simpleValue.stringValue();
    }
}
