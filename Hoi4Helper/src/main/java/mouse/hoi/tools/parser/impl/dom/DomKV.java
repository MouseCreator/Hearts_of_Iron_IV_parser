package mouse.hoi.tools.parser.impl.dom;

public record DomKV(DomSimple key, DomData value) {
    @Override
    public String toString() {
        return key + " = " + value;
    }
}
