package mouse.hoi.main.gfx.data;

import mouse.hoi.tools.parser.impl.reader.Inits;

import java.util.ArrayList;
import java.util.List;


public interface DataWrapper<T> extends Inits {
    List<T> getList();
    void setList(List<T> list);
    @Override
    default void initialize() {
        setList(new ArrayList<>());
    }
}
