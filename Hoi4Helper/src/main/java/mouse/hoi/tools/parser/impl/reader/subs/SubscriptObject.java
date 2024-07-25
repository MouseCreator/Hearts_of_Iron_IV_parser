package mouse.hoi.tools.parser.impl.reader.subs;

public interface SubscriptObject {
    String key();
    boolean isFinal();
    SubscriptObject child();
}
