package mouse.hoi.tools.parser.impl.reader.subs;

public record SubscriptObjectTop(String key, SubscriptObject child) implements SubscriptObject {
    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public String print() {
        return key + "." + child.print();
    }
}
