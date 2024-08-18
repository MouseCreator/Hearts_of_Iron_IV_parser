package mouse.hoi.tools.parser.impl.dom.query;

import mouse.hoi.tools.parser.impl.reader.lr.SimpleValue;

import java.util.function.Consumer;

public class SimpleResultQuery {
    private SimpleValue simpleValue;
    public SimpleResultQuery(SimpleValue val) {
        simpleValue = val;
    }

    public void setDouble(Consumer<Double> doubleConsumer) {
        doubleConsumer.accept(simpleValue.doubleValue());
    }

    public void setString(Consumer<String> stringConsumer) {
        stringConsumer.accept(simpleValue.stringValue());
    }

    public void setBoolean(Consumer<Boolean> setBoolean) {
        setBoolean.accept(simpleValue.boolValue());
    }
}
