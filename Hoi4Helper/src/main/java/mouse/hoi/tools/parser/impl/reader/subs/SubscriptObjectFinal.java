package mouse.hoi.tools.parser.impl.reader.subs;

public record SubscriptObjectFinal(String key) implements SubscriptObject {
    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public SubscriptObject child() {
        throw new UnsupportedOperationException("Cannot get a child node from subscript object with key: " + key);
    }
}
