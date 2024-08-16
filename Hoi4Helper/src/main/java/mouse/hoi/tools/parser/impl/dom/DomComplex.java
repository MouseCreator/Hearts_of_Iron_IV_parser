package mouse.hoi.tools.parser.impl.dom;


public class DomComplex implements DomData{

    private final DomComplexValue domData;

    public DomComplex(DomComplexValue d) {
        this.domData = d;
    }

    @Override
    public boolean isComplex() {
        return true;
    }

    @Override
    public DomComplex complex() {
        return this;
    }
    public DomComplexValue val() {
        return domData;
    }
}
