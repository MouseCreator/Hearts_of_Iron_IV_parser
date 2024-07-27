package mouse.hoi.tools.parser.impl.reader.lr;


public record ComplexValueImpl(LeftValue left, SimpleValue right, BlockValue block) implements ComplexValue {
    public ComplexValueImpl(LeftValue left, SimpleValue right, BlockValue block) {
        this.left = left;
        this.right = right;
        this.block = block;
    }
}
