package mouse.hoi.tools.parser.impl.reader.lr;

public interface ComplexValue {
    LeftValue left();
    SimpleValue right();
    BlockValue block();
}
