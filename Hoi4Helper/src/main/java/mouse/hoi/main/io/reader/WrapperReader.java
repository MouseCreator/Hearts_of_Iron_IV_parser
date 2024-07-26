package mouse.hoi.main.io.reader;

import mouse.hoi.main.gfx.data.DataWrapper;
import mouse.hoi.tools.context.Context;
import mouse.hoi.tools.parser.impl.reader.DataReader;
import mouse.hoi.tools.parser.impl.reader.helper.Readers;
import mouse.hoi.tools.parser.impl.reader.lr.LeftValue;
import mouse.hoi.tools.parser.impl.reader.lr.RightValue;

public abstract class WrapperReader<Y extends DataWrapper<T>, T> implements DataReader<Y> {
    public abstract String token();
    public abstract Class<T> wraps();
    private final Readers readers;
    public WrapperReader() {
        this.readers = Context.get().getBean(Readers.class);
    }
    @Override
    public void onKeyValue(Y wrapper, LeftValue leftValue, RightValue rightValue) {
        readers.lrValues().with(leftValue, rightValue).onToken(token())
                .mapBlock(b -> readers.interpreters().read(wraps(), b)).push(wrapper::getList).orElseThrow();
    }
}
